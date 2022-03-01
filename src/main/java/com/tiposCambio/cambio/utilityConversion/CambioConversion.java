package com.tiposCambio.cambio.utilityConversion;

import com.google.common.collect.Lists;
import com.tiposCambio.cambio.entity.CambioEntity;
import com.tiposCambio.cambio.getHttpRequest.GetHttpDataRequest;
import com.tiposCambio.cambio.model.Cambio;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CambioConversion {

@Autowired
private GetHttpDataRequest getHttpDataRequest;

   public String responseCambio(Cambio cambio) {
       DecimalFormat df = new DecimalFormat("#,###.##");
       String responseMonedaCambio1Resg = null;
       String responseMonedaCambio2Resg = null;
       String cambioEuroPorMoneda = null;
       Object responseAPICambio=getHttpDataRequest.getResponse();
       String response=responseAPICambio.toString();
       JSONObject jsonObject = new JSONObject(response);
       JSONObject jsonRate = jsonObject.getJSONObject ("rates");
       Map<String,Object>mapeoJson=null;
       mapeoJson=jsonRate.toMap();
       if(cambio.getNombreMoneda1().equalsIgnoreCase("EUR")) {
           responseMonedaCambio2Resg = mapeoJson.get(cambio.getNombreMoneda2()).toString();
           cambioEuroPorMoneda=responseMonedaCambio2Resg!=null?(df.format((Double.parseDouble(responseMonedaCambio2Resg))*(Double.parseDouble(cambio.getMontoMoneda1())))):"0.0";
       }else
       {
           responseMonedaCambio1Resg=mapeoJson.get(cambio.getNombreMoneda1())!=null?mapeoJson.get(cambio.getNombreMoneda1()).toString():"0";
           if(!responseMonedaCambio1Resg.equalsIgnoreCase("0")) {
               Double inversionCambio = Double.parseDouble(cambio.getMontoMoneda1()) / Double.parseDouble(responseMonedaCambio1Resg);
               responseMonedaCambio2Resg = String.valueOf(df.format(Double.parseDouble(mapeoJson.get(cambio.getNombreMoneda2()).toString()) * inversionCambio));
               cambioEuroPorMoneda = responseMonedaCambio2Resg;
           }else
               cambioEuroPorMoneda="0.0";
       }

       return String.valueOf(cambioEuroPorMoneda);
    }
}
