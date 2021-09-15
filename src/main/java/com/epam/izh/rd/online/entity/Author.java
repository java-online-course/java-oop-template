package com.epam.izh.rd.online.entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс содержащий информацию об авторе.
 * <p>
 * Необходимо:
 * 1) Создать список полей с указанными типами ровно в этом порядке:
 * - name с типом String и приватным модификатором доступа
 * - lastName с типом String и приватным модификатором доступа
 * - birthdate с типом LocalDate и приватным модификатором доступа
 * - country с типом String и приватным модификатором доступа
 * 2) Создать дефолтный конструктор (без параметров) (не забывайте alt+inset)
 * 3) Создать конструктор со всеми параметрами (в том порядке в котором перечислены) (не забывайте alt+inset)
 * 4) Создать геттеры и сеттеры (не забывайте alt+inset)
 * 5) Переопределить методы equals и hashCode - используйте генерацию (не забывайте alt+inset)
 * 6) Переопределить метод toString с выводом всех полей (не забывайте alt+inset)
 */
public class Author {
	private String name;
	private String lastName;
	private LocalDate birthdate;
	private String country;

	public Author() {
	}

	public Author(String name, String lastName, LocalDate birthdate, String country) {
		this.name = name;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || this.getClass() != o.getClass()) return false;
		Author author = (Author) o;
		return Objects.equals(this.getName(), author.getName()) &&
				Objects.equals(this.getLastName(), author.getLastName()) &&
				Objects.equals(this.getBirthdate(), author.getBirthdate()) &&
				Objects.equals(this.getCountry(), author.getCountry());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getName(), this.getLastName(), this.getBirthdate(), this.getCountry());
	}

	@Override
	public String toString() {
		return String.format("Author{name= %s, lastName= %s, birthdate= %s, country= %s}",
				this.getName(), this.getLastName(), this.getBirthdate(), this.getCountry());
	}
}
