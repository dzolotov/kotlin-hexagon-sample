<transactions>
  <#list txs as tx>
  <transaction comment="${tx.comment}" amount="${tx.amount}" datetime="${tx.datetime}"/>
  </#list>
</transactions>
