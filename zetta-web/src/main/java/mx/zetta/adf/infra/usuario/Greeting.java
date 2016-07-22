package mx.zetta.adf.infra.usuario;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Greeting")
public class Greeting implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlAttribute
    private long id;
    @XmlAttribute
    private String content;

    public Greeting() {

    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}