import com.hexagonkt.core.media.ApplicationMedia
import com.hexagonkt.http.client.HttpClient
import com.hexagonkt.http.client.jetty.JettyClientAdapter
import com.hexagonkt.serialization.SerializationManager
import com.hexagonkt.serialization.jackson.json.JsonFormat
import java.net.URL
import java.time.LocalDateTime

fun main() {
    GetTransactionsManager.adapters = mapOf("greenbank" to GetTransactionsAdapter())
    SerializationManager.formats = setOf(JsonFormat())
    assert(GetTransactionsManager.getTransactions("greenbank", "123", LocalDateTime.MIN)?.size==1)
}

interface GetTransactionsPort {
    fun getTransactions(bank: String, account: String, since: LocalDateTime): List<TransactionInfo>
}

class GetTransactionsAdapter : GetTransactionsPort {
    override fun getTransactions(bank: String, account: String, since: LocalDateTime): List<TransactionInfo> {
        val client = HttpClient(JettyClientAdapter(), baseUrl = URL("http://localhost:8080"))
        client.start()
        val result = client.get("/transactions/$bank/$account").body.toString()

        @Suppress("UNCHECKED_CAST")
        return SerializationManager.formatOf(ApplicationMedia.JSON).parse(result) as List<TransactionInfo>
    }
}

object GetTransactionsManager {
    var adapters = mapOf<String, GetTransactionsAdapter>()

    fun getTransactions(bank: String, account: String, since: LocalDateTime): List<TransactionInfo>? =
        adapters[bank]?.getTransactions(bank, account, since)
}