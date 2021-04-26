package tn.esprit.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.ForgotPasswordToken;
@Repository
public interface ForgotPasswordTokenRepository extends JpaRepository<ForgotPasswordToken, Long> {
	Optional<ForgotPasswordToken> findByToken(String token);
	Optional<ForgotPasswordToken> findByUser_Id(Long id);
}