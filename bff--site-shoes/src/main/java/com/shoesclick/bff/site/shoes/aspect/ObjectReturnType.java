package com.shoesclick.bff.site.shoes.aspect;

import com.shoesclick.bff.site.shoes.entity.ListPage;
import com.shoesclick.bff.site.shoes.exception.ElementNotFoundException;
import com.shoesclick.bff.site.shoes.exception.ListNotFoundException;
import com.shoesclick.bff.site.shoes.exception.ResourceException;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Optional;

public enum ObjectReturnType {
	
	 OPTIONAL {
                                 
		@Override
		public boolean isElementNotFound(Object objReturn) {                   
			return !Optional.class.cast(objReturn).isPresent();
		}

		@Override
		public ResourceException getException() {
		    return new ElementNotFoundException("Elemento não encontrado");
		}
	 },
	 OBJECT {

		@Override
		public boolean isElementNotFound(Object objReturn) {
			return objReturn == null;
		}

		 @Override
		 public ResourceException getException() {
			 return new ElementNotFoundException("Elemento não encontrado");
		 }

	 }, SPRING_PAGE {

		public boolean isElementNotFound(Object objReturn) {
			return Objects.isNull(objReturn) || ListPage.class.cast(objReturn).getContent().isEmpty();
		}

		@Override
		public ResourceException getException() {
			return new ListNotFoundException("Dados não retornado para Consulta");
		}
	}, COLLECTION {

		public boolean isElementNotFound(Object objReturn) {
			return Collection.class.cast(objReturn).isEmpty();
		}

		@Override
		public ResourceException getException() {
			return new ListNotFoundException("Dados não retornado para Consulta");
		}
	}, ENUMMERATION {

		public boolean isElementNotFound(Object objReturn) {
			return Enumeration.class.cast(objReturn).hasMoreElements();
		}

		@Override
		public ResourceException getException() {
			return new ListNotFoundException("Dados não retornado para Consulta");
		}
	};

	public abstract boolean isElementNotFound(Object objReturn);


	public abstract ResourceException getException();

}
