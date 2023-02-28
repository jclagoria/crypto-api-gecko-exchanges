package ar.com.api.exchanges.handler;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import ar.com.api.exchanges.dto.ExchangeDTO;
import ar.com.api.exchanges.model.Exchange;
import ar.com.api.exchanges.model.Ping;
import ar.com.api.exchanges.services.CoinGeckoServiceStatus;
import ar.com.api.exchanges.services.ExchangeApiService;
import ar.com.api.exchanges.utils.StringToInteger;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
@Slf4j
public class ExchangesApiHandler {
 
 private CoinGeckoServiceStatus serviceStatus;

 private ExchangeApiService serviceExchange;

 public Mono<ServerResponse> getStatusServiceCoinGecko(ServerRequest serverRequest) {

  log.info("In getStatusServiceCoinGecko");

  return ServerResponse
                .ok()
                .body(
                     serviceStatus.getStatusCoinGeckoService(), 
                     Ping.class);
 }

 public Mono<ServerResponse> getAllExchangesCoinGecko(ServerRequest sRequest) {

     log.info("In getAllExchangesCoinGecko");
     
     Optional<Integer> optPerPage = Optional.empty();

     if(sRequest.queryParam("perPage").isPresent()){
          optPerPage = Optional
                    .of(sRequest.queryParam("perPage")
                    .get()
                    .transform(StringToInteger.INSTANCE));
     }          
     
     ExchangeDTO filterDto = ExchangeDTO
                              .builder()
                              .perPage(optPerPage)                                   
                              .page(sRequest.queryParam("page"))
                              .build();
                              
     return ServerResponse
               .ok()
               .body(
                    serviceExchange.getAllExchanges(filterDto), 
                    Exchange.class);
 }

}
