# Wallet_Management
A monetary account holds the current balance for a player. The balance can be modified by registering transactions on the account, either debit transactions (removing funds) or credit transactions (adding funds). Create a REST API and an implementation that fulfils the requirements detailed below and honours the constraints.
<br />
• Current balance per player<br />
• Debit /Withdrawal per player A debit transaction will only succeed if there are
sufficient funds on the account (balance - debit amount >= 0).<br />
• The caller will supply a transaction id that must be unique for all transactions. If the
transaction id is not unique, the operation must fail.<br />
• Credit per player. The caller will supply a transaction id that must be unique for all
transactions. If the transaction id is not unique, the operation must fail.<br />
• Transaction history per player

# Technologies used in this Spring Boot Microservice Project
• Java Version: 8<br />
• Spring Boot Version: 2.7.1<br />
• Apache Mavan Version: 3.8.6<br />

# Dependencies used:
• Lombok<br />
• Spring Web<br />
• Rest Repositories<br />
• Thymeleaf<br />
• Spring Data JPA<br />
• H2 Database<br />

I have implemtented RestAPI to register the player, to create transactions (credit & debit) & to delete the player.
Here for transactions, I have implemented two methods.<br />

• to get list of the players<br />
GET method: /playerwallet<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture9.PNG)<br />

• to get details of individual player<br />
GET method: /playerwallet/{player_id}<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture10.PNG)<br />

• to register a player<br />
POST method: /playerwallet/<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture12.PNG)<br />

• to delete a player<br />
DELETE method: /playerwallet/{player_id}<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture20.PNG)<br />

• to get list of transactions of a player<br />
GET method: /playerwallet/{player_id}/transactions/<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture14.PNG)<br />

• to register a transaction (debit/credit) of a player<br />
POST method: /playerwallet/{player_id}/transactions/<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture9.PNG)<br />

• to register a credit transaction of a player<br />
POST method: /playerwallet/{player_id}/credittrans/{amount}<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture17.PNG)<br />

• to register a debit transaction of a player<br />
POST method: /playerwallet/{player_id}/debittrans/{amount}<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture19.PNG)<br />

# Frontend
I have kept the frontend of this project is versy simple. HTML & Thymeleaf are used to create the frontend. Here how it's looks like: <br /><br />
• Login / Register page (As I haven't used password for login, I have kept the Username field as unique)<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture.PNG)
• Home page after login, where all the transactions and current balance will be shown. <br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture1.PNG)

• if text input is given instead of number, it will show an error<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture2.PNG)
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture3.PNG)

• if transaction type is not selected, it will show an error message<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture4.PNG)
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture5.PNG)

• if debit transaction amount is greater than current balance, it will show an error message<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture6.PNG)
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture7.PNG)

• list of transactions & current balance<br /><br />
![alt text](https://github.com/nabaneeta5/Wallet_Management/blob/main/asset-img/Capture8.PNG)
