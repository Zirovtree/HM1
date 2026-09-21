package org.example
import kotlin.random.Random

fun main() {
}

// Первая часть
interface Attacker {
    val baseDamage: Int
    fun attack(target: Hero): Int
}

interface Spellcaster {
    var mana: Int
    fun castSpell(spellName: String): Boolean
}

// Вторая часть
abstract class Hero(val name: String, var hp: Int){
    abstract fun useSpecialAbility()
    fun takeDamage(damage: Int) {
        hp -= damage
        if (hp <= 0) {
            println("Персонаж $name погиб...")
        }
        else {
            println("У $name осталось $hp единиц здоровья")
        }
    }
}

// Третья часть
class Warrior(name: String, hp: Int): Hero(name, hp), Attacker {
    override val baseDamage: Int = 20
    val critDamage: Int = 30
    override fun attack(target: Hero): Int {
        val critChance = Random.nextInt(101) < 20
        if (critChance) {
            println("$name наносит критический урон $critDamage по ${target.name}")
            target.takeDamage(critDamage)
            return critDamage
        }
        else {
            println("$name наносит $baseDamage единиц урона по ${target.name}")
            target.takeDamage(baseDamage)
            return baseDamage
        }
    }
    override fun useSpecialAbility(){
        println("$name использует способность \"Второе дыхание\"")
        hp += 4
    }
}

class Mage(name: String, hp: Int): Hero(name, hp), Spellcaster {
    override var mana: Int = 100
    override fun castSpell(spellName: String): Boolean {
        if (mana >= 20) {
            hp += 10
            mana -= 20
            return true
        }
        else {
            println("Недостаточно маны")
            return false
        }
    }
    var specCount: Int = 3
    override fun useSpecialAbility() {
        if (specCount == 0) {
            println("Недостаточно очков умений")
        }
        else {
            mana += 50
            specCount -= 1
            println("Мана восстановлена на 50 единиц")
        }
    }
}

class Paladin(name: String, hp: Int): Hero(name, hp), Attacker, Spellcaster {
    override var mana: Int = 50
    override val baseDamage: Int = 10

    override fun attack(target: Hero): Int {
        println("$name наносит $baseDamage единиц урона по ${target.name}")
        target.takeDamage(baseDamage)
        return baseDamage
    }
    override fun castSpell(spellName: String): Boolean {
        if (mana >= 20) {
            hp += 10
            mana -= 20
            return true
        }
        else {
            println("Недостаточно маны")
            return false
        }
    }
    var specCount: Int = 2
    override fun useSpecialAbility() {
        if (specCount == 0) {
            println("Недостаточно очков умений")
        }
        else {
            mana += 30
            hp += 10
            specCount -= 1
            println("Мана восстановлена на 50 единиц")
        }
    }
}