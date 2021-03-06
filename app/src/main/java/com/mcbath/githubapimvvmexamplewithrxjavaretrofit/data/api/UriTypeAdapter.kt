package com.mcbath.githubapimvvmexamplewithrxjavaretrofit.data.api

import android.net.Uri
import com.google.gson.*
import java.lang.reflect.Type

/*Serialization to convert an object into a string, and
deserialization is its inverse operation (convert string -> object)*/

class UriTypeAdapter : JsonSerializer<Uri>,
    JsonDeserializer<Uri> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Uri {
        return Uri.parse(json.asString)
    }

    override fun serialize(
        src: Uri,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}