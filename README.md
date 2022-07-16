# wallet_management
A monetary account holds the current balance for a player. The balance can be modified by registering transactions on the account, either debit transactions (removing funds) or credit transactions (adding funds). Create a REST API and an implementation that fulfils the requirements detailed below and honours the constraints.

• Current balance per player
• Debit /Withdrawal per player A debit transaction will only succeed if there are
sufficient funds on the account (balance - debit amount >= 0).
The caller will supply a transaction id that must be unique for all transactions. If the
transaction id is not unique, the operation must fail.
• Credit per player. The caller will supply a transaction id that must be unique for all
transactions. If the transaction id is not unique, the operation must fail.
• Transaction history per player
