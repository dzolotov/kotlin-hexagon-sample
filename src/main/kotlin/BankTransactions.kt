import com.hexagonkt.core.media.ApplicationMedia
import com.hexagonkt.http.model.HttpStatus
import com.hexagonkt.http.server.HttpServerSettings
import com.hexagonkt.http.server.jetty.serve
import com.hexagonkt.serialization.SerializationManager
import com.hexagonkt.serialization.jackson.json.JsonFormat
import com.hexagonkt.serialization.serialize
import com.hexagonkt.templates.TemplateManager
import com.hexagonkt.templates.freemarker.FreeMarkerAdapter

fun main() {
    BankTransactionsManager.register("greenbank:.*".toRegex(), BankTransactionsGreenBankAdapter())
    TemplateManager.adapters = mapOf(".*".toRegex() to FreeMarkerAdapter)
    SerializationManager.formats = setOf(JsonFormat())
    serve(HttpServerSettings(bindPort = 8080)) {
        get("/transactions/{bank}/{id}") {
            val bank = pathParameters["bank"]
            val id = pathParameters["id"]
            val result = BankTransactionsManager.getTransaction("$bank:$id", java.time.LocalDateTime.MIN)
            if (result == null) {
                send(HttpStatus[500]!!)
            } else {
                ok(result.serialize(ApplicationMedia.JSON))
            }
        }
    }
}