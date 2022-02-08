import com.hexagonkt.core.logging.logger
import com.hexagonkt.templates.TemplateManager
import java.net.URL
import java.time.LocalDateTime

class BankTransactionsGreenBankTestAdapter : BankTransactionsPort {
    override fun getTransaction(account: String, since: LocalDateTime): List<TransactionInfo> {
        logger.info { "Get transaction for green bank since $since" }
        return listOf(
            TransactionInfo(100, LocalDateTime.now(), "Test transaction for one rub")
        )
    }
}