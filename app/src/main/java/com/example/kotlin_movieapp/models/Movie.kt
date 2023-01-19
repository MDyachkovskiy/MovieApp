package com.example.kotlin_movieapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id : Int = 0,
    var image : Int = 0,
    val name : String = "Название фильма",
    val description : String = ""
) : Parcelable

fun getTopMovies() : List<Movie> {
    return listOf(
        Movie(id = 0,
            image = 0,
            name = "2001 год: Космическая одиссея",
            description = "Экипаж космического корабля «Дискавери» — капитаны Дэйв Боумэн, Фрэнк" +
                    " Пул и их бортовой компьютер HAL 9000 — должны исследовать район галактики " +
                    "и понять, почему инопланетяне следят за Землей. На этом пути их ждет множество " +
                    "неожиданных открытий."
        ),
        Movie(id = 1,
            image = 0,
            name = "Чужой",
            description = "В далеком будущем возвращающийся на Землю грузовой космический корабль " +
                    "перехватывает исходящий с неизвестной планеты неопознанный сигнал. Экипаж, " +
                    "в соответствии с основными инструкциями, обязан найти и исследовать источник " +
                    "сигнала. Оказавшись на планете, астронавты повсюду обнаруживают неопознанные " +
                    "предметы, по виду напоминающие гигантские коконы."
        ),
        Movie(id = 2,
            image = 0,
            name = "Всё о Еве",
            description = "Стареющая звезда Бродвея Марго Ченнинг имела все: успех, друзей, " +
                    "молодого любовника. Скромная и трудолюбивая Ева становится ее помощницей, " +
                    "выполняющей все желания хозяйки. Быстрее всех понимает, что Евой движет " +
                    "желание стать «новой Марго Ченнинг», циничный театральный критик Аддисон " +
                    "Де Витт."

        ),
        Movie(id = 3,
            image = 0,
            name = "Амадей",
            description = "1781 год. Антонио Сальери успешно справляется с обязанностями придворного " +
                    "композитора при императоре Иосифе II. Когда же при дворе появляется Моцарт, " +
                    "Сальери к своему ужасу обнаруживает, что божественный музыкальный дар, которым " +
                    "он так желает обладать, был отпущен какому-то непристойному, проказливому шуту. " +
                    "Ослепленный завистью, он замышляет во что бы то ни стало уничтожить Моцарта..."

        ),
        Movie(id = 4,
            image = 0,
            name = "Амели",
            description = "Как полет крошечной мухи может вызвать где-то далеко мощный ураган, " +
                    "так и странные и, на первый взгляд, непонятные поступки тихой и одинокой " +
                    "девушки, живущей в мире своих фантазий, могут навсегда изменить жизнь " +
                    "совершенно разных людей, подарив им счастье и раскрасив окружающий мир " +
                    "яркими красками. Эту девушку зовут Амели Пулен."

        ),
        Movie(id = 5,
            image = 0,
            name = "Апокалипсис сегодня",
            description = "Во время Вьетнамской войны капитан Уиллард отправляется вверх по реке " +
                    "в Камбоджу со спецзаданием: найти и убить сумасшедшего полковника Курца, " +
                    "создавшего в отдалённом районе нечто вроде собственного культа. По пути " +
                    "капитан становится свидетелем безумия и ужасов войны."

        ),
        Movie(id = 6,
            image = 0,
            name = "Аватар",
            description = "Бывший морпех Джейк Салли прикован к инвалидному креслу. Несмотря на " +
                    "немощное тело, Джейк в душе по-прежнему остается воином. Он получает задание " +
                    "совершить путешествие в несколько световых лет к базе землян на планете Пандора, " +
                    "где корпорации добывают редкий минерал, имеющий огромное значение для выхода " +
                    "Земли из энергетического кризиса."

        )
    )
}
