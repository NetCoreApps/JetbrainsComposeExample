/* Options:
Date: 2023-10-12 15:08:12
Version: 6.110
Tip: To override a DTO option, remove "//" prefix before updating
BaseUrl: https://localhost:5001

//Package:
//AddServiceStackTypes: True
//AddResponseStatus: False
//AddImplicitVersion:
//AddDescriptionAsComments: True
//IncludeTypes:
//ExcludeTypes:
//InitializeCollections: True
//TreatTypesAsStrings:
//DefaultImports: java.math.*,java.util.*,net.servicestack.client.*
*/

import java.math.*
import java.util.*
import net.servicestack.client.*


@Route("/hello")
// @Route("/hello/{Name}")
open class Hello : IReturn<HelloResponse>
{
    var name:String? = null
    companion object { private val responseType = HelloResponse::class.java }
    override fun getResponseType(): Any? = Hello.responseType
}

open class SearchFiles : IReturn<SearchFilesResponse>
{
    var pattern:String? = null
    companion object { private val responseType = SearchFilesResponse::class.java }
    override fun getResponseType(): Any? = SearchFiles.responseType
}

open class HelloResponse
{
    var result:String? = null
}

open class SearchFilesResponse
{
    var results:ArrayList<String> = ArrayList<String>()
}