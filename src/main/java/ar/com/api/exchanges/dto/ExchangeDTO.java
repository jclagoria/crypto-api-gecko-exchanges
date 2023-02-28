package ar.com.api.exchanges.dto;

import java.util.Optional;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExchangeDTO implements IFilterDTO{

 private Optional<Integer> perPage;
 private Optional<String> page;
 

 @Override
 public String getUrlFilterString() {

  StringBuilder urlBuilder = new StringBuilder();

  if(perPage.isPresent())
   urlBuilder.append("?per_page=").append(perPage.get());
  
  if(page.isPresent())
   urlBuilder.append("&page=").append(page.get());
   
  
  return urlBuilder.toString();
 }
 
}
