package org.sheinbergon.aac.jna;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import lombok.RequiredArgsConstructor;
import org.sheinbergon.aac.jna.structure.*;

public class FdkAACLib {

    @RequiredArgsConstructor
    enum Methods {
        LIBRARY_INFO("aacEncGetLibInfo"),
        ENCODER_INFO("aacEncInfo"),
        SET_PARAM("aacEncoder_SetParam"),
        OPEN("accEncOpen"),
        CLOSE("accEncClose"),
        ENCODE("aacEncEncode");

        final String method;
    }

    private final static String FDK_AAC = "fdk-aac";

    static {
        Native.register(FDK_AAC);
    }

    static native int aacEncGetLibInfo(Pointer info);

    static native int aacEncOpen(PointerByReference handle, final int encModules, final int maxChannels);

    static native int aacEncClose(PointerByReference handle);

    static native int aacEncEncode(AACEncoder hAacEncoder, AACEncBufDesc inBufDesc, AACEncBufDesc outBufDesc, AACEncInArgs inargs, AACEncOutArgs outargs);

    static native int aacEncInfo(AACEncoder hAacEncoder, AACEncInfo pInfo);

    static native int aacEncoder_SetParam(AACEncoder encoder, int param, int value);
}