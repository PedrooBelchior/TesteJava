package br.com.testejava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Data
@Table(name = "TB_NUMEROS")
@AllArgsConstructor
@NoArgsConstructor
public class NumerosEntity implements Comparable {	

	@Id
	@Column(name = "NR_NUMERO")
	private Integer numero;

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
