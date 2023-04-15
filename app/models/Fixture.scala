package models

case class Fixture(date: String, homeTeam: String, awayTeam: String) {
  override val toString: String = s"$date $homeTeam VS $awayTeam"
}
