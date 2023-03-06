package com.jee.crud;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jee.crud.model.Command;
import com.jee.crud.model.Customer;
import com.jee.crud.model.PaymentStatusCommand;
import com.jee.crud.repository.CommandRepository;
import com.jee.crud.repository.PaymentStatusCommandRepository;

@Component
public class ScheduledTasks {
	
	@Autowired
    private CommandRepository commandRepository;
	
	@Autowired
    private PaymentStatusCommandRepository paymentStatusCommandRepository;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	// https://crontab.cronhub.io/
	// @Scheduled(cron = "0 0 21 * * *", zone = "Europe/Paris")  POUR TOUS LES JOURS A 21H
	
	@Scheduled(cron = "0 */5 * * * *", zone = "Europe/Paris") // Toutes les minutes
	public void reportCurrentTime() {
		List<PaymentStatusCommand> payments = paymentStatusCommandRepository.findAllPayment();
		
		if(!payments.isEmpty())
			System.out.println("Rapports des paiments non réglés :");
			for(PaymentStatusCommand payment: payments) {
				System.out.println(" |||-----");
				System.out.print(" ||| Les commandes [");
				List<Command> commands = commandRepository.findByPaymentStatusCommand(payment);
				Customer customer = null;
				for(Command command: commands) {
					System.out.print(" " + command.getId());
					if (customer==null) {
						customer = command.getCustomer();
					}
				}
				System.out.println(" ] du paiment "+ payment.getId()+" n'ont pas été payé par :");
				System.out.println(" ||| " + customer.getName() + " qui a l'id " + customer.getId());
				System.out.println(" |||-----");
			}
		
		System.out.println("The time is now " + dateFormat.format(new Date()));
	}
}