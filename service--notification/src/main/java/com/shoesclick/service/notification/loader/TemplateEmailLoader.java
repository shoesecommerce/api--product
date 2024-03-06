package com.shoesclick.service.notification.loader;

import com.shoesclick.service.notification.entity.TemplateEmail;
import com.shoesclick.service.notification.enums.TypeTemplate;
import com.shoesclick.service.notification.service.TemplateEmailService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class TemplateEmailLoader extends AbstractImportFileLoader {

    private final TemplateEmailService templateEmailService;

    public TemplateEmailLoader(TemplateEmailService templateEmailService) {
        this.templateEmailService = templateEmailService;
    }

    @Override
    protected void createItem(String linha) {
        String[] campos = linha.split(";");

        var actual = templateEmailService.findByTypeTemplate(TypeTemplate.valueOf(campos[0]));

        if (actual == null) {
            var tamplate = new TemplateEmail()
                    .setTypeTemplate(TypeTemplate.valueOf(campos[0]))
                    .setSubject(campos[1])
                    .setBody(campos[2]);
            templateEmailService.save(tamplate);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        importFile("load/template.txt");
    }
}
