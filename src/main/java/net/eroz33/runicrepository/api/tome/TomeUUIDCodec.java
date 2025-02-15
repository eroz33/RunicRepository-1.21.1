package net.eroz33.runicrepository.api.tome;

import com.mojang.serialization.Codec;

import java.util.UUID;

public final class TomeUUIDCodec {
    public static final Codec<TomeUUID> CODEC = Codec.STRING.xmap(
            string -> new TomeUUID(UUID.fromString(string)),    // read
            tomeUUID -> tomeUUID.id().toString()            // write
    );

    private TomeUUIDCodec() {}

}
