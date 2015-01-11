//
//  MarkdownDemoPage.java
//  markdown4j-gwt-demo
//
//  Created by William Shakour (billy1380) on 2 Jan 2015.
//  Copyright © 2015 SPACEHOPPER STUDIOS Ltd. All rights reserved.
//
package markdown4j.gwt.demo.client;

import org.markdown4j.client.GwtMarkdownProcessor;
import org.markdown4j.client.event.PluginContentReadyEventHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.shared.HandlerRegistration;
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
public class MarkdownDemoPage extends Composite implements PluginContentReadyEventHandler {

	private static MarkdownDemoPageUiBinder uiBinder = GWT.create(MarkdownDemoPageUiBinder.class);

	interface MarkdownDemoPageUiBinder extends UiBinder<Widget, MarkdownDemoPage> {}

	@UiField TextArea markdown;
	@UiField HTMLPanel converted;
	@UiField InlineHyperlink another;
	@UiField InlineHyperlink clear;

	@UiField TextArea asyncMarkdown;
	@UiField HTMLPanel asyncConverted;
	@UiField InlineHyperlink process;

	GwtMarkdownProcessor processor = new GwtMarkdownProcessor();
	GwtMarkdownProcessor asyncProcessor = new GwtMarkdownProcessor().registerAsyncPlugins();
	private HandlerRegistration registration;

	public MarkdownDemoPage() {
		initWidget(uiBinder.createAndBindUi(this));

		markdown.setText("# Ponat mortalia prior suo cum Lami adit\n" + "\n" + "## :thumbsup: Quod quas relictis tenuit\n" + "\n"
				+ "Lorem markdownum mutato Achivos **pectora tenebris**, inde nisi divesque,\n"
				+ "praetereunt favilla, et letum. Manu Idas praemia! Deae nec? Tam verba; agat quod\n"
				+ "dentemque amanti referunt :rose:, nova sed *minoribus habitus*.\n" + "\n"
				+ "    vfat_navigation.caps /= subdirectory(joystick_pci - fddi, computer +\n" + "            minicomputer_refresh);\n"
				+ "    remoteCore.rtAbendOdbc += power / designWeb - 4;\n" + "    if (widget_isp >= upnpToggleSystem(uatDsl(bar_activex_widget,\n"
				+ "            impactTutorialEps), 325247)) {\n" + "        control_keystroke_compile -= insertion_carrier;\n"
				+ "        drive_ping_heap.vdu_pop_source = website_sidebar_throughput;\n"
				+ "        hdmiInput.ip_compact(phreakingPublishingQuery, telecommunicationsMemory,\n" + "                sample + 4);\n" + "    }\n" + "\n"
				+ "Silvis densi comitante saepe imitamina unum lignea retemptat est ergo dictoque.\n"
				+ "Gerunt si aquarum per missus, in dedere ambiguis et :tennis:. Cum Turnus stetit.\n" + "Saepe undas.\n" + "\n" + "## Via concutit visa\n"
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
				+ "non castris. Gigantas suspiria :bath: ait colores in fretum semper et lateri?\n"
				+ "Petis corpore caput. Et tangit opposuit et meque domum inde ad sinuatoque\n"
				+ "triste, dixere turbantur Lycias foret sternitur stantem fessa, nequit. In\n"
				+ "comitesque ulla perstant curas reverti scelerata saxa ramum quibus quam ore\n" + "nomen cetera.");
		process();

		asyncMarkdown.setText("%%% yuml type=class scale=100 style=scruffy dir=LR format=png\n"
				+ "[Customer]<>1->*[Order], [Customer]-[note: Aggregate Root{bg:cornsilk}]\n" + "%%%\n" + "\n" + "---\n" + "\n"
				+ "%%% sequence style=modern-blue\n" + "title Authentication Sequence\n" + "\n" + "Alice->Bob: Authentication Request\n"
				+ "note right of Bob: Bob thinks about it\n" + "Bob->Alice: Authentication Response\n" + "%%%\n" + "\n" + "---\n" + "\n"
				+ "%%% include src=https://raw.github.com/vmg/redcarpet/4c14d0875163890e553897efcceb7480aa34f8e9/README.markdown %%%");

		onProcessClicked(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Composite#onAttach()
	 */
	@Override
	protected void onAttach() {
		super.onAttach();

		if (registration != null) {
			registration.removeHandler();
		}

		registration = asyncProcessor.addPluginContentReadyHandler(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Composite#onDetach()
	 */
	@Override
	protected void onDetach() {
		super.onDetach();

		if (registration != null) {
			registration.removeHandler();
		}
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

	@UiHandler("process")
	void onProcessClicked(ClickEvent ce) {
		asyncConverted.getElement().setInnerHTML(asyncProcessor.process(asyncMarkdown.getText()));
	}

	private void process() {
		HTMLPanel panel;
		int count = converted.getWidgetCount();

		if (count == 0) {
			converted.add(panel = new HTMLPanel(SafeHtmlUtils.EMPTY_SAFE_HTML));
		} else {
			panel = (HTMLPanel) converted.getWidget(count - 1);
		}

		panel.getElement().setInnerHTML(processor.process(markdown.getText()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.markdown4j.event.PluginContentReadyEventHandler#ready(org.markdown4j.event.PluginContentReadyEventHandler.PluginContentReadyEvent,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void ready(PluginContentReadyEvent event, String id, String content) {
		if (event.getSource() == processor) {
			throw new RuntimeException("Should not be getting events for the realtime processor");
		} else if (event.getSource() == asyncProcessor) {
			Element e = asyncConverted.getElementById(id);
			e.setInnerHTML(content);
		}
	}

}
