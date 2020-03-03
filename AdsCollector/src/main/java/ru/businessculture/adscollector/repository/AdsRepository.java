package ru.businessculture.adscollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.businessculture.adscollector.dto.Ads;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ads, Integer> {

}