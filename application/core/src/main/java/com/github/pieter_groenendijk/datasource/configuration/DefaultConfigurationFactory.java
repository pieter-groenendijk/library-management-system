package com.github.pieter_groenendijk.datasource.configuration;

import com.github.pieter_groenendijk.domain.entities.account.Account;
import com.github.pieter_groenendijk.domain.entities.loan.Loan;
import com.github.pieter_groenendijk.domain.entities.loan.LoanLimit;
import com.github.pieter_groenendijk.domain.entities.loan.LoansPerGenrePerMembership;
import com.github.pieter_groenendijk.domain.entities.loan.LoansPerGenrePerMembershipId;
import com.github.pieter_groenendijk.domain.entities.membership.Membership;
import com.github.pieter_groenendijk.domain.entities.membership.MembershipType;
import com.github.pieter_groenendijk.domain.entities.product.*;
import com.github.pieter_groenendijk.domain.entities.event.Event;
import com.github.pieter_groenendijk.domain.entities.event.LoanEvent;
import com.github.pieter_groenendijk.domain.entities.fine.Fine;
import com.github.pieter_groenendijk.domain.entities.fine.FineType;
import com.github.pieter_groenendijk.domain.entities.notification.LoanNotification;
import com.github.pieter_groenendijk.domain.entities.notification.Notification;
import com.github.pieter_groenendijk.domain.entities.payment.Payment;
import com.github.pieter_groenendijk.domain.entities.payment.PaymentStatus;
import com.github.pieter_groenendijk.domain.entities.reservation.Reservation;
import org.hibernate.cfg.Configuration;

public class DefaultConfigurationFactory {
    public Configuration create() {
        ConfigurationBuilder builder = new ConfigurationBuilder();

        setMisc(builder);
        setConnection(builder);

        setAnnotatedClasses(builder);

        return builder.build();
    }

    private void setAnnotatedClasses(ConfigurationBuilder builder) {
        builder
            .addAnnotatedClass(Account.class)
            .addAnnotatedClass(Membership.class)
            .addAnnotatedClass(MembershipType.class)
            .addAnnotatedClass(Loan.class)
            .addAnnotatedClass(ProductCopy.class)
            .addAnnotatedClass(ProductTemplate.class)
            .addAnnotatedClass(PhysicalProductTemplate.class)
            .addAnnotatedClass(PhysicalProduct.class)
            .addAnnotatedClass(Reservation.class)
            .addAnnotatedClass(Event.class)
// TODO: Add below annotated classes, currently not possible since Loan and all those other classes are not placed here yet.
//            .addAnnotatedClass(ReservationEvent.class)
            .addAnnotatedClass(LoanEvent.class)
            .addAnnotatedClass(Genre.class)
            .addAnnotatedClass(LoanLimit.class)
            .addAnnotatedClass(Notification.class)
            .addAnnotatedClass(LoanNotification.class)
            .addAnnotatedClass(FineType.class)
            .addAnnotatedClass(PaymentStatus.class)
            .addAnnotatedClass(Payment.class)
            .addAnnotatedClass(Fine.class)
            .addAnnotatedClass(LoansPerGenrePerMembershipId.class)
            .addAnnotatedClass(LoansPerGenrePerMembership.class);

        ;
    }

    private void setMisc(ConfigurationBuilder builder) {
        builder
            .setDriver("org.postgresql.Driver")
            .setDialect("org.hibernate.dialect.PostgreSQLDialect")
            .setSchemaHandlingMethod("validate")
            .setPoolSize("10")
            .setFormattingOfSQL(true)
            .setShowingOfSQL(true)
            .setQuotingOfIdentifiers(true);
    }

    private void setConnection(ConfigurationBuilder builder) {
        builder
            .setConnectionURL(
                "postgresql",
                getConnectionHostname(),
                getConnectionPort(),
                getConnectionDatabaseName()
            )
            .setConnectionAuthentication(
                getConnectionUsername(),
                getConnectionPassword()
            );
    }

    private String getConnectionHostname() {
        return System.getenv("DATABASE_HOST");
    }

    private String getConnectionPort() {
        return System.getenv("DATABASE_PORT");
    }

    private String getConnectionDatabaseName() {
        return System.getenv("DATABASE_NAME");
    }

    private String getConnectionUsername() {
        return System.getenv("DATABASE_USER");
    }

    private String getConnectionPassword() {
        return System.getenv("DATABASE_PASSWORD");
    }
}
