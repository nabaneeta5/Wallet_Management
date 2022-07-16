# Wallet_Management
A monetary account holds the current balance for a player. The balance can be modified by registering transactions on the account, either debit transactions (removing funds) or credit transactions (adding funds). Create a REST API and an implementation that fulfils the requirements detailed below and honours the constraints.

• Current balance per player
• Debit /Withdrawal per player A debit transaction will only succeed if there are
sufficient funds on the account (balance - debit amount >= 0).
The caller will supply a transaction id that must be unique for all transactions. If the
transaction id is not unique, the operation must fail.
• Credit per player. The caller will supply a transaction id that must be unique for all
transactions. If the transaction id is not unique, the operation must fail.
• Transaction history per player

It is a Spring Boot Micoservice Project.
• Java Version: 8
• Spring Boot Version: 2.7.1
• Apache Mavan Version: 3.8.6

Dependencies I have used:
• Lombok
• Spring Web
• Rest Repositories
• Thymeleaf
• Spring Data JPA
• H2 Database

I have implemtented RestAPI to register the player, to create transactions (credit & debit), to delete the player.
Here for transactions, I have implemented two methods.

• to get list of the players
GET method: /playerwallet/
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture9.PNG)
• to get details of one player
GET method: /playerwallet/{player_id}

• to register a player
POST method: /playerwallet/

• to delete a player
DELETE method: /playerwallet/{player_id}

• to get list of transactions of a player
GET method: /playerwallet/{player_id}/transactions/

• to register a transaction (debit/credit) of a player
POST method: /playerwallet/{player_id}/transactions/

• to register a credit transaction of a player
POST method: /playerwallet/{player_id}/credittrans/{amount}

• to register a debit transaction of a player
POST method: /playerwallet/{player_id}/debittrans/{amount}

![alt text](http://url/to/img.png)
