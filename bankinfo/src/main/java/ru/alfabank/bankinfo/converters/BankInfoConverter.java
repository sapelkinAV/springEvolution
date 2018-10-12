package ru.alfabank.bankinfo.converters;

import com.thomas_bayer.blz.DetailsType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.alfabank.bankinfo.model.BankBlzInfo;
import ru.alfabank.model.BankInformationModel;

@Mapper(componentModel = "spring")
public interface BankInfoConverter {

    @Mappings({
            @Mapping(target = "bezeichnung"),
            @Mapping(target = "bic"),
            @Mapping(target = "ort"),
            @Mapping(target = "plz")
    })
    BankBlzInfo mapToBankBlzInfo(DetailsType detailsType);

    @Mappings({
            @Mapping(target = "bezeichnung"),
            @Mapping(target = "bic"),
            @Mapping(target = "ort"),
            @Mapping(target = "plz")
    })
    BankInformationModel mapToBankInformationModel(BankBlzInfo blzInfo);

}
