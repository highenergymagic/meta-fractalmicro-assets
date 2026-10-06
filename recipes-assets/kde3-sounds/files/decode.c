// SPDX-License-Identifier: MIT
/* Integer-only decoding; explicit little-endian PCM on every build host. */
#include <tremor/ivorbisfile.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
static void u16(FILE *f, unsigned n) { fputc(n & 255,f); fputc((n>>8)&255,f); }
static void u32(FILE *f, uint32_t n) { u16(f,n&65535); u16(f,n>>16); }
int main(int argc,char **argv) {
 OggVorbis_File vf;
 FILE *in, *out;
 short pcm[4096];
 _Static_assert(sizeof(short)==2, "Tremor PCM requires 16-bit short");
 long got;
 unsigned bytes=0;
 int stream=0;
 if(argc!=3 || !(in=fopen(argv[1],"rb"))) return 1;
 if(ov_open(in,&vf,NULL,0)) { fclose(in); return 1; }
 vorbis_info *info=ov_info(&vf,-1);
 if(ov_streams(&vf)!=1 || !info || info->rate!=44100 || info->channels!=2) {
  fprintf(stderr,"Expected one 44100 Hz stereo Vorbis stream\n");
  ov_clear(&vf); return 1;
 }
 ogg_int64_t frames=ov_pcm_total(&vf,-1);
 if(frames<=0 || frames>44100*10) { ov_clear(&vf); return 1; }
 out=fopen(argv[2],"wb");
 if(!out) { ov_clear(&vf); return 1; }
 fwrite("RIFF",1,4,out); u32(out,36+(uint32_t)frames*4);
 fwrite("WAVEfmt ",1,8,out); u32(out,16); u16(out,1); u16(out,2);
 u32(out,44100); u32(out,176400); u16(out,4); u16(out,16);
 fwrite("data",1,4,out); u32(out,(uint32_t)frames*4);
 while((got=ov_read(&vf,(char *)pcm,sizeof(pcm),&stream))>0) {
  if(stream!=0 || got%4 || bytes+(unsigned)got>(unsigned)frames*4) {
   fclose(out); ov_clear(&vf); return 1;
  }
  for(long i=0;i<got/2;i++) u16(out,(uint16_t)pcm[i]);
  bytes+=(unsigned)got;
 }
 int failed=got<0 || bytes!=(unsigned)frames*4 || ferror(out);
 if(fclose(out)) failed=1;
 ov_clear(&vf);
 return failed;
}
