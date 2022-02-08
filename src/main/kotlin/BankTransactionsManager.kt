import java.time.LocalDateTime

object BankTransactionsManager {
    var adapters = mutableMapOf<Regex, BankTransactionsPort>()

    fun register(regex: Regex, bankTransactionsPort: BankTransactionsPort) {
        adapters[regex] = bankTransactionsPort
    }

    fun getTransaction(account: String, since: LocalDateTime): List<TransactionInfo>? {
        return adapters.filter { it.key.matches(account) }.firstNotNullOfOrNull { it.value }
            ?.getTransaction(account, since)
    }
}