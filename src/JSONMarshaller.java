import java.util.ArrayList;
import java.util.List;

import org.cpntools.accesscpn.model.Arc;
import org.cpntools.accesscpn.model.HLDeclaration;
import org.cpntools.accesscpn.model.Label;
import org.cpntools.accesscpn.model.Page;
import org.cpntools.accesscpn.model.PetriNet;
import org.cpntools.accesscpn.model.Place;
import org.cpntools.accesscpn.model.Transition;
import org.cpntools.accesscpn.model.importer.DOMParser;
import org.cpntools.accesscpn.model.importer.NetDeclarationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Node;

public class JSONMarshaller {
    private static JSONArray marshalLabels(List<Label> labels) {
        JSONArray json = new JSONArray();
        for (Label label: labels)
            json.put(Utils.trimAnnotation(label));
        return json;
    }


    private static JSONArray marshalPlaces(Iterable<Place> iterable) {
        JSONArray json = new JSONArray();
        for (Place place: iterable)
            json.put(
                new JSONObject()
                .put("id", place.getId())
                .put("name", Utils.trimAnnotation(place.getName()))
                .put("marking", Utils.trimAnnotation(place.getInitialMarking()))
                .put("labels", marshalLabels(place.getLabel()))
            );
        return json;
    }

    private static JSONArray marshalTransitions(Iterable<Transition> iterable) {
        JSONArray json = new JSONArray();
        for (Transition transition: iterable)
            json.put(
                new JSONObject()
                .put("id", transition.getId())
                .put("name", Utils.trimAnnotation(transition.getName()))
                .put("conditions", Utils.trimAnnotation(transition.getCondition()))
                .put("priority", Utils.trimAnnotation(transition.getPriority()))
                .put("code", Utils.trimAnnotation(transition.getCode()))
                .put("time", Utils.trimAnnotation(transition.getTime()))
                .put("labels", marshalLabels(transition.getLabel()))
            );
        return json;
    }

    private static JSONArray marshalArcs(Iterable<Arc> iterable) {
        JSONArray json = new JSONArray();
        for (Arc arc: iterable)
            json.put(
                new JSONObject()
                .put("id", arc.getId())
                .put("inscription", Utils.trimAnnotation(arc.getHlinscription()))
                .put("kind", arc.getKind())
                .put("source", arc.getSource().getId())
                .put("target", arc.getTarget().getId())
            );
        return json;
    }

    private static JSONObject marshalPage(Page page) {
        return new JSONObject()
            .put("id", page.getId())
            .put("name", Utils.trimAnnotation(page.getName()))
            .put("places", marshalPlaces(page.place()))
            .put("transitions", marshalTransitions(page.transition()))
            .put("arcs", marshalArcs(page.getArc()));
    }

    private static JSONArray marshalDeclarations(Iterable<HLDeclaration> declarations) {
        final JSONArray json = new JSONArray();
        for (HLDeclaration declaration: declarations)
            json.put(
                new JSONObject()
                .put("id", declaration.getId())
                .put("text", declaration.getText())
                .put("structure", declaration.getStructure().asString())
            );
        return json;
    }

    public static String marshal(PetriNet net) {
        final JSONObject json = new JSONObject();
        json.put("id", net.getId());
        json.put("declarations", marshalDeclarations(net.declaration()));
        // json.put("isTimed", net.isTimed());
        json.put("kind", net.getKind());
        json.put("labels", marshalLabels(net.getLabel()));
        json.put("name", net.getName());

        final JSONArray jsonPages = new JSONArray();
        for (Page page : net.getPage()) {
            jsonPages.put(
                new JSONObject()
                .put("id", page.getId())
                .put("name", Utils.trimAnnotation(page.getName()))
                .put("data", marshalPage(page))
            );
        }
        json.put("pages", jsonPages);

        return json.toString(2);
    }
}
