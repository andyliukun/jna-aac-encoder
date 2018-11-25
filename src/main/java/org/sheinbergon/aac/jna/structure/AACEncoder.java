package org.sheinbergon.aac.jna.structure;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.ptr.ShortByReference;
import org.sheinbergon.aac.jna.util.JNAUtil;

import java.util.List;

/**
 * Maps to AACENCODER struct in @see <a href="https://github.com/mstorsjo/fdk-aac/blob/v0.1.6/libAACenc/src/aacenc_lib.cpp">fdk-aac/libAACenc/src/aacenc_lib.cpp</a>
 */
public class AACEncoder extends Structure {

    private final static int MAX_TOTAL_EXT_PAYLOADS = 12;
    private final static int MAX_PAYLOAD_SIZE = 256;

    private final static List<String> FIELD_ORDER = JNAUtil.structureFieldOrder(AACEncoder.class);

    // TODO - Protect against null values
    public static AACEncoder of(PointerByReference pointerReference) {
        return new AACEncoder(pointerReference.getValue());
    }

    private AACEncoder(Pointer pointer) {
        super(pointer);
        setAlignType(ALIGN_NONE); // Make sure field size alignments are as expected
        read(); // Read once after initialize from provided pointer
    }

    public UserParam extParam;
    public CoderConfig coderConfig;
    public AACEncConfig aacConfig;
    public Pointer hAacEnc;
    public Pointer hEnvEnc;
    public Pointer hMetadataEnc;
    public int metaDataAllowed;
    public Pointer hMpsEnc;
    public Pointer hTpEnc;
    public ShortByReference inputBuffer;
    public ByteByReference outBuffer;
    public int inputBufferSize;
    public int inputBufferSizePerChannel;
    public int outBufferInBytes;
    public int inputBufferOffset;
    public int nSamplesToRead;
    public int nSamplesRead;
    public int nZerosAppended;
    public int nDelay;
    public int nDelayCore;
    /*
        Arrays - 1D, 2D or even 3D, are initialized as 1D to accommodate for size allocation.
        This is due to the fact JNA doesn't support mapping multi-dimensional array inside structs with simple
        preservation of size/memory allocation for primitives. As we won't be using these values, I'm
        perfectly fine with it
     */
    public AACEncExtPayload[] extPayload = new AACEncExtPayload[MAX_TOTAL_EXT_PAYLOADS];
    public int InitFlags;
    public int nMaxAacElements;
    public int nMaxAacChannels;
    public int nMaxSbrElements;
    public int nMaxSbrChannels;
    public int encoder_modis;
    public int CAPF_tpEnc;

    @Override
    protected List<String> getFieldOrder() {
        return FIELD_ORDER;
    }
}
