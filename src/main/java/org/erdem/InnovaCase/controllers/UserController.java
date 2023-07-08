package org.erdem.InnovaCase.controllers;

import org.erdem.InnovaCase.model.Transaction;
import org.erdem.InnovaCase.model.User;
import org.erdem.InnovaCase.service.AuthService;
import org.erdem.InnovaCase.service.TransactionService;
import org.erdem.InnovaCase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
    UserService usersService;
	@Autowired
	AuthService authService;
	@Autowired
	TransactionService transactionService;

	@GetMapping("/public")
	public String publicAccess() {
		return "Public Content.";
	}

	@GetMapping("/user")
	@PreAuthorize("hasAuthority('USER')")
	public String userAccess(Authentication authentication) {
		System.out.println(authentication.getName());

		return "User Content.";
	}

	@GetMapping("/sendMoney")
	@PreAuthorize("hasAuthority('USER')")
	public String sendMoney(Authentication authentication, @RequestParam String toWho, @RequestParam double amount){
		User user = authService.findByEmail(authentication.getName());

		Transaction transaction = new Transaction(user.getFirstName(),toWho,amount);
		transactionService.saveTransaction(transaction);

		user.getTransactions().add(transaction);

		usersService.updateUser(user);

		return null;
	}

	@GetMapping("/getTotalTransactions")
	@PreAuthorize("hasAuthority('USER')")
	public double totalSpending(Authentication authentication){
		User user = authService.findByEmail(authentication.getName());

		return usersService.getTotalSpending(user.getId());


	}


	@GetMapping("/getMyTransactions")
	@PreAuthorize("hasAuthority('USER')")
	public String allSpendings(Authentication authentication){
		User user = authService.findByEmail(authentication.getName());
		List<Transaction> transactions = user.getTransactions();

		return transactions.toString();


	}


	@GetMapping("getTransactionsOnDate")
	@PreAuthorize("hasAuthority('USER')")
	public String spendingsOnDate(Authentication authentication, @RequestParam int year, int month, int day){
		User user = authService.findByEmail(authentication.getName());
		List<Transaction> filteredTransactions = new ArrayList<>();


		for (int i=0; i<user.getTransactions().size();i++){
			LocalDate targetDate = LocalDate.of(year,month,day);

			LocalDate date = user.getTransactions().get(i).getLocalDate();

			if (date.isEqual(targetDate)){
				filteredTransactions.add(user.getTransactions().get(i));
			}

		}
		return filteredTransactions.toString();

	}


	public void scheduled_job_5(){
		List<User> users = usersService.findAll();

		for (int i=0; i<users.size(); i++){

			User user = users.get(i);
			user.setTotalSpending(usersService.getTotalSpending(user.getId()));
			usersService.updateUser(user);




		}
	}




	/*
	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	 */
}
