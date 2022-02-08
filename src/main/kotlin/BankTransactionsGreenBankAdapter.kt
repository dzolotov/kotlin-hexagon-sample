import com.hexagonkt.core.logging.logger
import com.hexagonkt.templates.TemplateManager
import java.net.URL
import java.time.LocalDateTime

class BankTransactionsGreenBankAdapter : BankTransactionsPort {
    override fun getTransaction(account: String, since: LocalDateTime): List<TransactionInfo> {
        logger.info { "Get transaction for green bank since $since" }
        return listOf(
            TransactionInfo(100000, LocalDateTime.now(), "Sample transaction for 1000 rub")
        )
    }
}