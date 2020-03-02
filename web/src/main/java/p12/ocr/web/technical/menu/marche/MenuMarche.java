package p12.ocr.web.technical.menu.marche;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
public @Data class MenuMarche {

    @NonNull
    private String organization;

    @NonNull
    private String gender;

    @NonNull
    private String name;

    @NonNull
    private String url;
}
