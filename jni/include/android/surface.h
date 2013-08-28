#ifndef ANDROID_SURFACE_WRAPPER_H
#define ANDROID_SURFACE_WRAPPER_H

#include <stdint.h>
#include <jni.h>

#define ANDROID_SURFACE_RESULT_SUCCESS						 0
#define ANDROID_SURFACE_RESULT_NOT_VALID					-1
#define ANDROID_SURFACE_RESULT_COULDNT_LOCK					-2
#define ANDROID_SURFACE_RESULT_COULDNT_UNLOCK_AND_POST		-3
#define ANDROID_SURFACE_RESULT_COULDNT_INIT_BITMAP_SURFACE	-4
#define ANDROID_SURFACE_RESULT_COULDNT_INIT_BITMAP_CLIENT	-5
#define ANDROID_SURFACE_RESULT_JNI_EXCEPTION				-6

#ifdef __cplusplus
extern "C" {
#endif

int AndroidSurface_register(JNIEnv* env, jobject jsurface);

int AndroidSurface_getPixels(int width, int height, void** pixels);
	
int AndroidSurface_updateSurface();
	
int AndroidSurface_unregister();
	
#ifdef __cplusplus
}
#endif

#endif

