import java.time.LocalDateTime

data class TransactionInfo(val amount:Int, val datetime: LocalDateTime, val comment:String)

interface BankTransactionsPort {
    fun getTransaction(account: String, since: LocalDateTime): List<TransactionInfo>
}
