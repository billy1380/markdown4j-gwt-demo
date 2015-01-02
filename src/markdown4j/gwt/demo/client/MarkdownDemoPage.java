//
//  MarkdownDemoPage.java
//  markdown4j-gwt-demo
//
//  Created by William Shakour (billy1380) on 2 Jan 2015.
//  Copyright © 2015 SPACEHOPPER STUDIOS Ltd. All rights reserved.
//
package markdown4j.gwt.demo.client;

import java.io.IOException;

import org.markdown4j.Markdown4jProcessor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author William Shakour (billy1380)
 *
 */
public class MarkdownDemoPage extends Composite {

	private static MarkdownDemoPageUiBinder uiBinder = GWT.create(MarkdownDemoPageUiBinder.class);

	interface MarkdownDemoPageUiBinder extends UiBinder<Widget, MarkdownDemoPage> {}

	@UiField TextArea markdown;
	@UiField HTMLPanel converted;
	@UiField InlineHyperlink another;
	@UiField InlineHyperlink clear;

	Markdown4jProcessor processor = new Markdown4jProcessor();

	public MarkdownDemoPage() {
		initWidget(uiBinder.createAndBindUi(this));

		markdown.setText("# Ponat mortalia prior suo cum Lami adit\n" + "\n" + "## Quod quas relictis tenuit\n" + "\n"
				+ "Lorem markdownum mutato Achivos **pectora tenebris**, inde nisi divesque,\n"
				+ "praetereunt favilla, et letum. Manu Idas praemia! Deae nec? Tam verba; agat quod\n"
				+ "dentemque amanti referunt vidit, nova sed *minoribus habitus*.\n" + "\n"
				+ "    vfat_navigation.caps /= subdirectory(joystick_pci - fddi, computer +\n" + "            minicomputer_refresh);\n"
				+ "    remoteCore.rtAbendOdbc += power / designWeb - 4;\n" + "    if (widget_isp >= upnpToggleSystem(uatDsl(bar_activex_widget,\n"
				+ "            impactTutorialEps), 325247)) {\n" + "        control_keystroke_compile -= insertion_carrier;\n"
				+ "        drive_ping_heap.vdu_pop_source = website_sidebar_throughput;\n"
				+ "        hdmiInput.ip_compact(phreakingPublishingQuery, telecommunicationsMemory,\n" + "                sample + 4);\n" + "    }\n" + "\n"
				+ "Silvis densi comitante saepe imitamina unum lignea retemptat est ergo dictoque.\n"
				+ "Gerunt si aquarum per missus, in dedere ambiguis et hausit. Cum Turnus stetit.\n" + "Saepe undas.\n" + "\n" + "## Via concutit visa\n"
				+ "\n" + "Voce matrem, ebiberant ferus, curvamine genus, inspicitur? Tulimus molliaque\n"
				+ "occasus tibi? Neptunius illa solet miratur, est classe ab nullum fortiter.\n"
				+ "*Amori* mea geminis suumque feres ego doleret causa; citi, matris [stantibus\n"
				+ "fratre](http://www.thesecretofinvisibility.com/), sum fata.\n" + "\n"
				+ "    if (fragmentation_pci(tag_pdf / vlog_transistor_program, 52, printer) !=\n" + "            localhost) {\n"
				+ "        directory_hibernate_plagiarism += crossplatform_pci(adsl_pdf.operating(\n" + "                metaTabletReality), 3);\n" + "    }\n"
				+ "    if (kernelHackerEngine == friend_e_process) {\n" + "        bootFile = isp;\n" + "    }\n" + "    if (backup_left) {\n"
				+ "        data *= dynamic(-3, -5, primaryVoip) - 1;\n" + "        url_in_compression = characterSidebar;\n" + "    } else {\n"
				+ "        basic(resolutionDomain, 71);\n" + "    }\n" + "\n" + "## Tartara ad animum fluviis ventis terga moresque\n" + "\n"
				+ "Suas ferrataque audenti tollit nec. Ante illi sed sonant *iaculo*, Noctisque\n" + "quinos silva, in tollit per possum est submisso cepit.\n"
				+ "\n" + "1. Ante cursus inpono praemia cultumque tetigere auro\n" + "2. Tollere tecta\n" + "3. Nulla dixit tersis in Isthmon marem\n"
				+ "4. Simul viridem senectae Memnonides super rogabam flores\n" + "5. Gravidae nomine\n" + "6. Ora trepident pectore molior quem\n" + "\n"
				+ "[Sternit](http://zeus.ugent.be/) temptare, valet dispositam ripis te dixi\n"
				+ "[irascentemque paulatim](http://jaspervdj.be/) praebentem eunt miserere, retraxi\n"
				+ "non castris. Gigantas suspiria genuit ait colores in fretum semper et lateri?\n"
				+ "Petis corpore caput. Et tangit opposuit et meque domum inde ad sinuatoque\n"
				+ "triste, dixere turbantur Lycias foret sternitur stantem fessa, nequit. In\n"
				+ "comitesque ulla perstant curas reverti scelerata saxa ramum quibus quam ore\n" + "nomen cetera.");
		process();
	}

	@UiHandler("markdown")
	void onMarkdownKeyUp(KeyUpEvent kue) {
		process();
	}

	@UiHandler("another")
	void onAnotherClicked(ClickEvent ce) {
		if (markdown.getText().length() > 0) {
			process();
			converted.add(new HTMLPanel(SafeHtmlUtils.EMPTY_SAFE_HTML));
			markdown.setText("");
		}
	}

	@UiHandler("clear")
	void onClearClicked(ClickEvent ce) {
		markdown.setText("");
		converted.clear();
	}

	private void process() {
		HTMLPanel panel;
		String processed = "";

		try {
			processed = processor.process(markdown.getText());
		} catch (IOException e) {
			GWT.log("Error processing", e);
		}

		int count = converted.getWidgetCount();

		if (count == 0) {
			converted.add(panel = new HTMLPanel(SafeHtmlUtils.EMPTY_SAFE_HTML));
		} else {
			panel = (HTMLPanel) converted.getWidget(count - 1);
		}

		panel.getElement().setInnerHTML(processed);
	}
}
