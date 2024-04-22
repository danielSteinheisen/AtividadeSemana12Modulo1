package br.com.fullstack.education.atividadeseman12modulo1.dto;

import lombok.Data;

@Data
public class MediaGeralDTO {

    private Double mediaGeral;

    public Double getMediaGeral() {
        return mediaGeral;
    }

    public void setMediaGeral(Double mediaGeral) {
        this.mediaGeral = mediaGeral;
    }
}
