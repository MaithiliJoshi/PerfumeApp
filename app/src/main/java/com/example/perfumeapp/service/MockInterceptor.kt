package com.example.perfumeapp.service

import android.util.Log
import com.example.myperfumeapp.BuildConfig
import okhttp3.*

/**
 * This will help us to test our networking code while a particular API is not implemented
 * yet on Backend side.
 */
class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("Interceptor","In ")
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()
            Log.d("Interceptor","uri is ${uri}")
            val responseString = when {
                uri.endsWith("getAllPerfumes") -> getListOfPerfumes
                else -> ""
            }

            Log.d("Interceptor","got response string")

            var responseFinal = chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                    responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
            Log.d("Intereceptor","built response ")
            return responseFinal

        } else {
            //just to be on safe side.
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }

}

const val getListOfPerfumes = """
[{
	perfumeId: 1,
    imageUrl: "",
    perfumeName: "Coolwater",
    perfumeCategory: "Aqua",
    perfumeBrand: "Davidoff",
    price: 4600
},
{
	perfumeId: 2,
    imageUrl: "",
    perfumeName: "Yellow Diamond",
    perfumeCategory: "Floral",
    perfumeBrand: "Versace",
    price: 5200
},
{
	perfumeId: 3,
    imageUrl: "",
    perfumeName: "CK One",
    perfumeCategory: "Fruity",
    perfumeBrand: "Calvin Klein",
    price: 5100
}
]
"""