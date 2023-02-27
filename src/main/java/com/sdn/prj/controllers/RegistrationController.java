package com.sdn.prj.controllers;



import com.sdn.prj.entities.JsonResponse;
import com.sdn.prj.entities.Person;
import com.sdn.prj.services.PersonService;
import com.sdn.prj.services.SmsService;
import com.sdn.prj.utils.PromocodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sdn")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistrationController {

    private final PersonService service;
    private final SmsService smsService;

    private final PromocodeGenerator promocodeGenerator;



    @Autowired
    public RegistrationController(PersonService service, SmsService smsService, PromocodeGenerator promocodeGenerator) {
        this.service = service;
        this.smsService = smsService;
        this.promocodeGenerator = promocodeGenerator;
    }

    @PostMapping("/registration")
    public JsonResponse register(@RequestParam("name") String iin,
                                 @RequestParam("email") String name,
                                 @RequestParam("email-2") String number){
        boolean exists = service.save(iin,name,number);
        if(!exists){
            String code = promocodeGenerator.generatePromoCode();
            service.setPromocode(iin,code);
            smsService.send_sms(number, "Ваш промокод "+ code, 1, "", "", 0, "", "");
            return new JsonResponse(false, code);
        }else{
            return new JsonResponse(true, null);
        }
    }

    @PostMapping("/recovery")
    public JsonResponse recovery(@RequestParam("name-2") String iin){

        if(service.existsByIin(iin)){
            Person person = service.findByIin(iin);
            String code = person.getPromocode();
            String number = person.getPhone();
            smsService.send_sms(number, "Ваш промокод "+ code, 1, "", "", 0, "", "");
        }

        return new JsonResponse(service.existsByIin(iin), null);
    }
}
