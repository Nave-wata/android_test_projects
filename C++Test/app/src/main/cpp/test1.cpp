#include <string>
#include <jni.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_ctest2_MainActivity_getMessage(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}