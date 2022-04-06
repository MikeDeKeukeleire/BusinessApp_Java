package repository;

import java.util.ArrayList;
import java.util.List;

import domein.Categorie;
import domein.Datasource;
import domein.Gebruiker;
import domein.MvoDoelstelling;
import domein.Sdg;
import domein.SubMvoDoelstelling;
import domein.SubSdg;

public class PopulateDB {

	public void run() {
		GenericDaoJPA<Gebruiker> gebruikerDao = new GenericDaoJPA<>(Gebruiker.class);
		GenericDaoJPA.startTransaction();

		gebruikerDao.insert(new Gebruiker(1, "coordinator", "Test123", "MvoCoordinator"));

		GenericDaoJPA.commitTransaction();

		// DATASOURCES TOEVOEGEN

		GenericDaoJPA<Datasource> datasourceDao = new GenericDaoJPA<>(Datasource.class);

		List<String> testLijst3 = List.of(
				"Verbruiksjaar;Hoofdgemeente;Energie;Injectie_Afname;Straat;Regio;Aantal_toegangspunten;Benaderend_Verbruik_kWh",
				"2020;AALST;ELEKTRICITEIT;AFNAME;'T MAEGELIJNPLEIN;Regio 1;3;40697.68",
				"2020;AALST;ELEKTRICITEIT;AFNAME;'T SPIEKEN;Regio 1;23;82135.10",
				"2020;AALST;ELEKTRICITEIT;AFNAME;'T VESTJEN;Regio 1;23;111525.04",
				"2020;AALST;ELEKTRICITEIT;AFNAME;1 MEISTRAAT;Regio 1;52;140143.61",
				"2020;AALST;ELEKTRICITEIT;AFNAME;AARTSTRAAT;Regio 1;57;384466.39",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ABBEELSTRAAT;Regio 1;23;64334.31",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ABDIJSTRAAT;Regio 1;42;150673.42",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ABRAHAMSWEG;Regio 1;11;22531.05",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ACACIASTRAAT;Regio 1;42;90080.71",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ACHTERBREMT;Regio 1;19;65060.31",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ACHTERMAAL;Regio 1;45;266661.24",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ACHTERSTRAAT;Regio 1;73;274173.27",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ACHTERWEG;Regio 1;48;212550.54",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ACHTZALIGHEDEN;Regio 1;10;41599.20",
				"2020;AALST;ELEKTRICITEIT;AFNAME;AFFLIGEMDREEF;Regio 1;143;708433.14",
				"2020;AALST;ELEKTRICITEIT;AFNAME;AFSPANNINGSSTRAAT;Regio 1;45;115502.05",
				"2020;AALST;ELEKTRICITEIT;AFNAME;AJUINENSTRAAT;Regio 1;28;92449.13",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ALBERT LIENARTSTRAAT;Regio 1;95;306636.12",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ALBRECHTLAAN;Regio 1;110;2074334.25",
				"2020;AALST;ELEKTRICITEIT;AFNAME;ALDEGONDISSTRAAT;Regio 1;6;37481.49");

		List<String> testLijst4 = List.of(
				"Verbruiksjaar;Hoofdgemeente;Energie;Injectie_Afname;Straat;Regio;Aantal_toegangspunten;Benaderend_Verbruik_kWh",
				"2020;GENT;GAS;AFNAME;AAIGEMSTRAAT;Regio 1;127;1563373.98",
				"2020;GENT;GAS;AFNAME;AALBESSENLAAN;Regio 1;14;202712.43",
				"2020;GENT;GAS;AFNAME;AALSCHOLVERSTRAAT;Regio 1;11;170405.34",
				"2020;GENT;GAS;AFNAME;AAMBEELDSTRAAT;Regio 1;57;616690.40",
				"2020;GENT;GAS;AFNAME;AAN DE BOCHT;Regio 1;22;583278.79",
				"2020;GENT;GAS;AFNAME;AAN DE LINDE;Regio 1;6;121241.51",
				"2020;GENT;GAS;AFNAME;AAN DE RATTE;Regio 1;35;325629.81",
				"2020;GENT;GAS;AFNAME;AANNEMERSSTRAAT;Regio 1;209;2329997.48",
				"2020;GENT;GAS;AFNAME;ABDIJMOLENSTRAAT;Regio 1;65;875229.82",
				"2020;GENT;GAS;AFNAME;ABDISSTRAAT;Regio 1;22;307469.45",
				"2020;GENT;GAS;AFNAME;ABEELSTRAAT;Regio 1;48;716329.72",
				"2020;GENT;GAS;AFNAME;ABRAHAMSTRAAT;Regio 1;27;568271.11",
				"2020;GENT;GAS;AFNAME;ABRIKOOSSTRAAT;Regio 1;71;649719.61",
				"2020;GENT;GAS;AFNAME;ABSDALESTRAAT;Regio 1;31;492326.39",
				"2020;GENT;GAS;AFNAME;ACACIASTRAAT;Regio 1;67;1135763.67",
				"2020;GENT;GAS;AFNAME;ACADEMIESTRAAT;Regio 1;8;78966.89",
				"2020;GENT;GAS;AFNAME;ACHILLES HEYNDRICKXLAAN;Regio 1;4;548127.27",
				"2020;GENT;GAS;AFNAME;ACHILLES MUSSCHESTRAAT;Regio 1;104;1555040.35",
				"2020;GENT;GAS;AFNAME;ACHTENE;Regio 1;4;68143.16",
				"2020;GENT;GAS;AFNAME;ACHTENKOUTERSTRAAT;Regio 1;55;743314.39",
				"2020;GENT;GAS;AFNAME;ACHTERDRIES;Regio 1;63;816379.90");

		GenericDaoJPA.startTransaction();

		// Data toevoegen aan tabel
		datasourceDao.insert(new Datasource("Datasource 1", testLijst3));
		datasourceDao.insert(new Datasource("Datasource 2", testLijst4));

		GenericDaoJPA.commitTransaction();

		// MVOs
		GenericDaoJPA.startTransaction();
		GenericDaoJPA<SubMvoDoelstelling> subMvoDao = new GenericDaoJPA<>(SubMvoDoelstelling.class);
		SubMvoDoelstelling subMvo1 = new SubMvoDoelstelling(1, "TestSubMvo1",
				"https://cdn-icons-png.flaticon.com/512/2111/2111421.png", null);
		subMvoDao.insert(subMvo1);
		SubMvoDoelstelling subMvo2 = new SubMvoDoelstelling(2, "TestSubMvo2",
				"https://cdn-icons-png.flaticon.com/512/337/337946.png", null);
		subMvoDao.insert(subMvo2);
		SubMvoDoelstelling subMvo3 = new SubMvoDoelstelling(3, "TestSubMvo3",
				"https://cdn-icons-png.flaticon.com/512/733/733641.png", null);
		subMvoDao.insert(subMvo3);
		SubMvoDoelstelling subMvo4 = new SubMvoDoelstelling(4, "TestSubMvo4",
				"https://cdn-icons-png.flaticon.com/512/149/149071.png", null);
		subMvoDao.insert(subMvo4);
		SubMvoDoelstelling subMvo5 = new SubMvoDoelstelling(5, "TestSubMvo5",
				"https://cdn-icons-png.flaticon.com/512/202/202308.png", null);
		subMvoDao.insert(subMvo5);
		GenericDaoJPA.commitTransaction();

		GenericDaoJPA.startTransaction();
		GenericDaoJPA<MvoDoelstelling> mvoDao = new GenericDaoJPA<>(MvoDoelstelling.class);
		MvoDoelstelling mvo1 = new MvoDoelstelling(1, "TestMvo1",
				"https://cdn-icons-png.flaticon.com/512/3143/3143442.png", List.of(subMvo1));
		mvoDao.insert(mvo1);
		mvoDao.insert(new MvoDoelstelling(2, "TestMvo2", "https://cdn-icons-png.flaticon.com/512/149/149060.png",
				List.of(subMvo2)));
		mvoDao.insert(new MvoDoelstelling(3, "TestMvo3", "https://cdn-icons-png.flaticon.com/512/7164/7164007.png",
				List.of(subMvo3, subMvo4)));
		mvoDao.insert(new MvoDoelstelling(4, "TestMvo4", "https://cdn-icons-png.flaticon.com/512/7180/7180357.png",
				List.of(subMvo5)));
		GenericDaoJPA.commitTransaction();

		GenericDaoJPA.startTransaction();

		GenericDaoJPA<SubSdg> subSdgDao = new GenericDaoJPA<>(SubSdg.class);

		subSdgDao.insert(new SubSdg(1, "1.1",
				"By 2030, reduce at least by half the proportion of men, women and children of all ages living in poverty in all its dimensions according to national definitions",
				null, List.of(mvo1)));

		subSdgDao.insert(new SubSdg(2, "1.2",
				"Implement nationally appropriate social protection systems and measures for all, including floors, and by 2030 achieve substantial coverage of the poor and the vulnerable",
				null, null));

		subSdgDao.insert(new SubSdg(4, "2.1",
				"Adopt measures to ensure the proper functioning of food commodity markets and their derivatives and facilitate timely access to market information, including on food reserves, in order to help limit extreme food price volatility.",
				null, null));

		subSdgDao.insert(new SubSdg(5, "3.1",
				"By 2030, reduce the global maternal mortality ratio to less than 70 per 100,000 live births", null,
				null));

		subSdgDao.insert(new SubSdg(6, "4.1",
				"By 2030, ensure that all girls and boys complete free, equitable and quality primary and secondary education leading to relevant and Goal-4 effective learning outcomes",
				null, null));

		subSdgDao.insert(new SubSdg(7, "5.1", "End all forms of discrimination against all women and girls everywhere",
				null, null));

		subSdgDao.insert(new SubSdg(8, "5.2",
				"Eliminate all forms of violence against all women and girls in the public and private spheres, including trafficking and sexual and other types of exploitation",
				null, null));

		subSdgDao.insert(new SubSdg(9, "6.1",
				"By 2030, achieve universal and equitable access to safe and affordable drinking water for all", null,
				null));

		subSdgDao.insert(new SubSdg(10, "6.2",
				"By 2030, achieve access to adequate and equitable sanitation and hygiene for all and end open defecation, paying special attention to the needs of women and girls and those in vulnerable situations",
				null, null));

		subSdgDao.insert(new SubSdg(11, "7.1",
				"By 2030, ensure universal access to affordable, reliable and modern energy services", null, null));

		subSdgDao.insert(new SubSdg(12, "8.1",
				"Sustain per capita economic growth in accordance with national circumstances and, in particular, at least 7 per cent gross domestic product growth per annum in the least developed countries",
				null, null));

		subSdgDao.insert(new SubSdg(13, "9.1",
				"Develop quality, reliable, sustainable and resilient infrastructure, including regional and transborder infrastructure, to support economic development and human well-being, with a focus on affordable and equitable access for all",
				null, null));

		subSdgDao.insert(new SubSdg(14, "9.2",
				"Promote inclusive and sustainable industrialization and, by 2030, significantly raise industrys share of employment and gross domestic product, in line with national circumstances, and double its share in least developed countries",
				null, null));

		subSdgDao.insert(new SubSdg(15, "10.1",
				"By 2030, progressively achieve and sustain income growth of the bottom 40 per cent of the population at a rate higher than the national average",
				null, null));

		subSdgDao.insert(new SubSdg(16, "10.2",
				"By 2030, empower and promote the social, economic and political inclusion of all, irrespective of age, sex, disability, race, ethnicity, origin, religion or economic or other status",
				null, null));

		subSdgDao.insert(new SubSdg(17, "11.1",
				"By 2030, ensure access for all to adequate, safe and affordable housing and basic services and upgrade slums",
				null, null));

		subSdgDao.insert(new SubSdg(18, "12.1",
				"By 2030, achieve the sustainable management and efficient use of natural resources", null, null));

		subSdgDao.insert(new SubSdg(19, "13.1",
				"Strengthen resilience and adaptive capacity to climate-related hazards and natural disasters in all countries",
				null, null));

		subSdgDao.insert(new SubSdg(20, "13.2",
				"Integrate climate change measures into national policies, strategies and planning", null, null));

		subSdgDao.insert(new SubSdg(21, "14.1",
				"By 2025, prevent and significantly reduce marine pollution of all kinds, in particular from land-based activities, including marine debris and nutrient pollution",
				null, null));

		subSdgDao.insert(new SubSdg(22, "14.2",
				"Minimize and address the impacts of ocean acidification, including through enhanced scientific cooperation at all levels",
				null, null));

		subSdgDao.insert(new SubSdg(23, "15.1",
				"Take urgent action to end poaching and trafficking of protected species of flora and fauna and address both demand and supply of illegal wildlife products",
				null, null));

		subSdgDao.insert(new SubSdg(24, "16.1",
				"Significantly reduce all forms of violence and related death rates everywhere", null, null));

		subSdgDao.insert(new SubSdg(25, "17.1",
				"Strengthen domestic resource mobilization, including through international support to developing countries, to improve domestic capacity for tax and other revenue collection",
				null, null));

		subSdgDao.insert(new SubSdg(26, "17.2",
				"Mobilize additional financial resources for developing countries from multiple sources", null, null));

		List<SubSdg> subSdgNoPoverty = new ArrayList<SubSdg>();
		subSdgNoPoverty.add(subSdgDao.findByNummering("1.1"));
		subSdgNoPoverty.add(subSdgDao.findByNummering("1.2"));

		List<SubSdg> subSdgZeroHunger = new ArrayList<SubSdg>();
		subSdgZeroHunger.add(subSdgDao.findByNummering("2.1"));

		List<SubSdg> subSdgGoodhealthandwellbeing = new ArrayList<SubSdg>();
		subSdgGoodhealthandwellbeing.add(subSdgDao.findByNummering("3.1"));

		List<SubSdg> subSdgQualityeducation = new ArrayList<SubSdg>();
		subSdgQualityeducation.add(subSdgDao.findByNummering("4.1"));

		List<SubSdg> subSdgGenderEquality = new ArrayList<SubSdg>();
		subSdgGenderEquality.add(subSdgDao.findByNummering("5.1"));
		subSdgGenderEquality.add(subSdgDao.findByNummering("5.2"));

		List<SubSdg> subSdgCleanwaterandsanitation = new ArrayList<SubSdg>();
		subSdgCleanwaterandsanitation.add(subSdgDao.findByNummering("6.1"));
		subSdgCleanwaterandsanitation.add(subSdgDao.findByNummering("6.2"));

		List<SubSdg> subSdgAffordableandcleanenergy = new ArrayList<SubSdg>();
		subSdgAffordableandcleanenergy.add(subSdgDao.findByNummering("7.1"));

		List<SubSdg> subSdgDecentworkandeconomicgrowth = new ArrayList<SubSdg>();
		subSdgDecentworkandeconomicgrowth.add(subSdgDao.findByNummering("8.1"));

		List<SubSdg> subSdgIndustryinnovationandinfrastructure = new ArrayList<SubSdg>();
		subSdgIndustryinnovationandinfrastructure.add(subSdgDao.findByNummering("9.1"));
		subSdgIndustryinnovationandinfrastructure.add(subSdgDao.findByNummering("9.2"));

		List<SubSdg> subSdgReduceinqualities = new ArrayList<SubSdg>();
		subSdgReduceinqualities.add(subSdgDao.findByNummering("10.1"));
		subSdgReduceinqualities.add(subSdgDao.findByNummering("10.2"));

		List<SubSdg> subSdgSustainablecitiesandcommunities = new ArrayList<SubSdg>();
		subSdgSustainablecitiesandcommunities.add(subSdgDao.findByNummering("11.1"));

		List<SubSdg> subSdgResponsibleconsumptionandproduction = new ArrayList<SubSdg>();
		subSdgResponsibleconsumptionandproduction.add(subSdgDao.findByNummering("12.1"));

		List<SubSdg> subSdgClimateaction = new ArrayList<SubSdg>();
		subSdgClimateaction.add(subSdgDao.findByNummering("13.1"));
		subSdgClimateaction.add(subSdgDao.findByNummering("13.2"));

		List<SubSdg> subSdgLifebelowwater = new ArrayList<SubSdg>();
		subSdgLifebelowwater.add(subSdgDao.findByNummering("14.1"));
		subSdgLifebelowwater.add(subSdgDao.findByNummering("14.2"));

		List<SubSdg> subSdgLifeonland = new ArrayList<SubSdg>();
		subSdgLifeonland.add(subSdgDao.findByNummering("15.1"));

		List<SubSdg> subSdgPeacejusticeandstronginstitutions = new ArrayList<SubSdg>();
		subSdgPeacejusticeandstronginstitutions.add(subSdgDao.findByNummering("16.1"));

		List<SubSdg> subSdgPartnershipforthegoals = new ArrayList<SubSdg>();
		subSdgPartnershipforthegoals.add(subSdgDao.findByNummering("17.1"));
		subSdgPartnershipforthegoals.add(subSdgDao.findByNummering("17.2"));

		GenericDaoJPA.commitTransaction();

		GenericDaoJPA.startTransaction();

		GenericDaoJPA<Sdg> sdgDao = new GenericDaoJPA<>(Sdg.class);

		Sdg aa = new Sdg(1, 1, "No poverty", "UrlIcoon", subSdgNoPoverty);
		sdgDao.insert(aa);

		Sdg bb = new Sdg(2, 2, "Zero Hunger", "UrlIcoon", subSdgZeroHunger);
		sdgDao.insert(bb);

		Sdg cc = new Sdg(3, 3, "Good health and well-being", "UrlIcoon", subSdgGoodhealthandwellbeing);
		sdgDao.insert(cc);

		Sdg dd = new Sdg(4, 4, "Quality education", "UrlIcoon", subSdgQualityeducation);
		sdgDao.insert(dd);

		Sdg ee = new Sdg(5, 5, "Gender equality", "UrlIcoon", subSdgGenderEquality);
		sdgDao.insert(ee);

		Sdg ff = new Sdg(6, 6, "Clean water and sanitation", "UrlIcoon", subSdgCleanwaterandsanitation);
		sdgDao.insert(ff);

		Sdg gg = new Sdg(7, 7, "Affordable and clean energy", "UrlIcoon", subSdgAffordableandcleanenergy);
		sdgDao.insert(gg);

		Sdg hh = new Sdg(8, 8, "Decent work and economic growth", "UrlIcoon", subSdgDecentworkandeconomicgrowth);
		sdgDao.insert(hh);

		Sdg ii = new Sdg(9, 9, "Industry, innovation and infrastructure", "UrlIcoon",
				subSdgIndustryinnovationandinfrastructure);
		sdgDao.insert(ii);

		Sdg jj = new Sdg(10, 10, "Reduce inequalities", "UrlIcoon", subSdgReduceinqualities);
		sdgDao.insert(jj);

		Sdg kk = new Sdg(11, 11, "Sustainable cities and communities", "UrlIcoon",
				subSdgSustainablecitiesandcommunities);
		sdgDao.insert(kk);

		Sdg ll = new Sdg(12, 12, "Responsible consumption and production", "UrlIcoon",
				subSdgResponsibleconsumptionandproduction);
		sdgDao.insert(ll);

		Sdg mm = new Sdg(13, 13, "Climate action", "UrlIcoon", subSdgClimateaction);
		sdgDao.insert(mm);

		Sdg nn = new Sdg(14, 14, "Life below water", "UrlIcoon", subSdgLifebelowwater);
		sdgDao.insert(nn);

		Sdg oo = new Sdg(15, 15, "Life on land", "UrlIcoon", subSdgLifeonland);
		sdgDao.insert(oo);

		Sdg pp = new Sdg(16, 16, "Peace, justice and string institutions", "UrlIcoon",
				subSdgPeacejusticeandstronginstitutions);
		sdgDao.insert(pp);

		Sdg qq = new Sdg(17, 17, "Partnerships for the goals", "UrlIcoon", subSdgPartnershipforthegoals);
		sdgDao.insert(qq);

		List<Sdg> cat1 = new ArrayList<Sdg>();
		cat1.add(aa);
		cat1.add(bb);
		List<Sdg> cat2 = new ArrayList<Sdg>();
		cat2.add(cc);
		cat2.add(dd);

		GenericDaoJPA<Categorie> catDao = new GenericDaoJPA<>(Categorie.class);
		catDao.insert(new Categorie(1, "Economic", "https://cdn-icons-png.flaticon.com/512/3310/3310624.png", cat1,
				subSdgNoPoverty));
		catDao.insert(new Categorie(2, "Environment", "https://cdn-icons-png.flaticon.com/512/2427/2427589.png", cat2,
				subSdgGoodhealthandwellbeing));

		GenericDaoJPA.commitTransaction();

	}
}