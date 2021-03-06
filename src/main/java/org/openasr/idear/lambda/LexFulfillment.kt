package org.openasr.idear.lambda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.openasr.idear.intents.*
import org.openasr.idear.lex.*
class LexFulfillment : RequestHandler<LexFulfillmentRequest, LexFulfillmentResponse> {

    override fun handleRequest(request: LexFulfillmentRequest, context: Context): LexFulfillmentResponse {
        context.logger.log(request.toString())

        val intentName: String = request.currentIntent?.name ?: return noMatch()

        val response: LexFulfillmentResponse = when (intentName) {
            "HiIdea" -> hiIdea(intentName, request.sessionAttributes)
            "Navigate" -> navigate()
            "OpenView" -> openView(intentName, request.currentIntent?.slots)
            "Run" -> run()
            "WhereAmI" -> whereAmI()
            "Focus" -> focus(intentName, request.currentIntent?.slots)
            "Goto" -> goto(request.currentIntent?.slots)
            "Expand" -> expand()
            "Shrink" -> shrink()
            "PressKey" -> pressKey(request.currentIntent?.slots)

            "AddNewClass" -> addNewClass()
            else -> noMatch()
        }

        context.logger.log(response.toString())
        return response
    }
}

