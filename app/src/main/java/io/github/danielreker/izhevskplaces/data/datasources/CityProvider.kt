package io.github.danielreker.izhevskplaces.data.datasources

import io.github.danielreker.izhevskplaces.model.Category
import io.github.danielreker.izhevskplaces.model.City
import io.github.danielreker.izhevskplaces.model.Recommendation

object CityProvider {
    private val city = City(id = "izhevsk", name = "Ижевск")

    private val categories = listOf(
        Category("parks", "Парки"),
        Category("museums", "Музеи"),
        Category("malls", "Торговые центры"),
        Category("restaurants", "Рестораны"),
    )

    private val recommendations = listOf(
        Recommendation("park-kirova", "parks", "Парк культуры им. С.М. Кирова", "Один из старейших парков Ижевска, открытый на берегу городского пруда в далеком 1933 году. Привычное место горожан для совершения неспешных прогулок. Летом начинают работать аттракционы, зимой – открывается каток. На центральной аллее возвышается памятник С.М. Кирову. На его территории находится знаменитый Ижевский зоопарк. Памятник букве Ё – любимое место для фотографирования местных жителей и гостей города. В глубине из массивных бревен обустроена птичья столовая, где в самые голодные дни можно подкормить пернатых обитателей.", "park-gorkogo"),
        Recommendation("park-gorkogo", "parks", "Летний сад им. М. Горького", "Старейший парк Ижевска, открытый на берегу Ижевского пруда в 1857 году. Расположен в исторической части города. Поначалу был Летним садом, прилегающим к дому известного Генерала, а в советский период стал для всех горожан. В 40-е годы 20 века здесь появились первые качели, и сад окончательно превратился в место, где для жителей города стали проводиться праздники и фестивали. Сейчас на территории множество аттракционов и развлекательных объектов, небольшой фонтан, Дерево любви, музей Ижевска, Кошкин дом.", "park-kirova"),
    )

    fun getCity(): City = city

    fun getAllCategories(): List<Category> = categories

    fun getRecommendationsByCategory(categoryId: String): List<Recommendation> {
        return recommendations.filter { it.categoryId == categoryId }
    }

    fun getRecommendationById(recommendationId: String): Recommendation? {
        return recommendations.singleOrNull { it.id == recommendationId }
    }
}