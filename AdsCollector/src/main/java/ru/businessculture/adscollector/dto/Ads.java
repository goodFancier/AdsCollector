package ru.businessculture.adscollector.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Ads {
    // Идентификатор записи
    @Id
    protected Integer id;

    // Url объявления на сайте-источнике
    protected String url;

    // Заголовок
    protected String title;

    // Цена
    protected Float price;

    // Дата и время добавления объявления в нашу систему, либо время обновления. Время московское
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected Date time;

    // Телефон
    protected String phone;

    // Название мобильного оператора
    protected String phone_operator;

    // Персона для контактов, автор объявления
    protected String person;

    // Контактное лицо. В основном бывает указано, если поле person содержит имя какой-нибудь компании.
    protected String contactname;

    // Тип персоны для контактов. "Частное лицо", "Агентство" или "Частное лицо (фильтр)"
    protected String person_type;

    // ID типа персоны для контактов. 1 - "Частное лицо", 2 - "Агентство" или 3 - "Частное лицо (фильтр)"
    protected Integer person_type_id;

    // Регион и Город вместе, для получения отдельных значений смотрите поля region и city1
    protected String city;

    // Метро или район
    protected String metro;

    // Адрес
    protected String address;

    // Описание объявления
    // protected String description;

    // Тип недвижимости: Продам, Сдам, Куплю или Сниму
    protected String nedvigimost_type;

    // ID типа недвижимости: 1 - Продам, 2 - Сдам, 3 - Куплю или 4 - Сниму
    protected Integer nedvigimost_type_id;

    // ID объявления на сайте-источнике
    protected String avitoid;

    // Сайт-источник
    protected String source;

    // ID cайта-источника в нашей системе
    protected Integer source_id;

    // Картинки. Массив объектов, каждый из которых имеет поле imgurl - адрес картинки
    // protected ArrayList<Image> images;

    // params	Дополнительные параметры объявления
    // Количество комнат
    @Column(name = "room_count")
    protected String param_1945;

    // Вид объекта (вторичка/новостройка)
    @Column(name = "sales_kind")
    protected String param_1957;

    // Этаж
    @Column(name = "floor")
    protected Integer param_2113;

    // Этажей в доме
    @Column(name = "floors_in_building")
    protected Integer param_2213;

    // Площадь
    @Column(name = "area")
    protected Float param_2313;

    // Площадь кухни
    @Column(name = "kitchen_area")
    protected Float param_12721;

    // Жилая площадь
    @Column(name = "live_area")
    protected Float param_12722;

    // Технология строительства
    @Column(name = "house_material")
    protected String param_2009;

    // Вид коммерческого объекта (гостиница/офис/...)
    @Column(name = "commercial_object_kind")
    protected String param_4869;

    // Площадь коммерческого объекта
    @Column(name = "commercial_object_area")
    protected Float param_4920;

    // Этаж коммерческого объекта
    @Column(name = "commercial_object_floor")
    protected Integer param_12868;

    // Этажность сдания коммерческого объекта
    @Column(name = "commercial_object_floors_in_building")
    protected Integer param_12869;

    // Название жилого комплекса
    @Column(name = "building_name")
    protected String param_12882;

    // Срок сдачи новостройки
    @Column(name = "build_end_date")
    protected String param_12867;

    // ID категории первого уровня, например, категория Недвижимость имеет значение 1
    protected Integer cat1_id;

    // ID категории второго уровня, например, категория Квартиры имеет значение 2
    protected Integer cat2_id;

    // Название категории первого уровня, например, Недвижимость
    protected String cat1;

    // Название категории второго уровня, например, Квартиры
    protected String cat2;

    // Объект, содержащий координаты объявления, поля lat и lng
    protected Coordinates coords;

    // Только название региона
    protected String region;

    // Только название города
    protected String city1;

    // param_xxx	Дополнительный параметр с кодом xxx, коды параметров смотрите в разделе Параметры всех категорий

    // Количество объявлений с тем же номером. Имеет значение только для категории Недвижимость и подкатегорий, для других равно null.
    protected Integer count_ads_same_phone;

    // Показывает, защищен (подменён) ли телефон, актуально для объявлений с avito, realty.yandex.ru и cian, для других источников равно null.
    // Возможные значения: 1 - телефон защищен, 0 - не защищен, null - параметр недоступен.
    protected Integer phone_protected;

    // Регион мобильного телефона.
    protected String phone_region;
}
