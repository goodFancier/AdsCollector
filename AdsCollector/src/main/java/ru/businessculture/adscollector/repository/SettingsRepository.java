package ru.businessculture.adscollector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.businessculture.adscollector.dto.Setting;

public interface SettingsRepository  extends JpaRepository<Setting, String>
{
}
