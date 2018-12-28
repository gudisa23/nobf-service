package com.amind.att;

/**
 *  interface for Excel Header Column Names
 */
public interface QualToolConstants {

    /**
     *  Excel Header Column Names
     */

    public static final String COMMON_PRODUCT_AGNOSTIC_LOC_INFO[] = {"Postal / LEC Valid","LOC Address", "LOC City", "LOC State", "LOC ZIP",
            "Phone #", "LOC Building", "LOC Room", "LOC Floor","NPA-NXX","NPA-NXX Split Indicator"};

    public static final String DSL_UBV_IPBB_HSI_PRODUCT_AGNOSTIC_LOC_INFO[] = {"Postal / LEC Valid","LOC Address", "LOC City", "LOC State", "LOC ZIP",
            "Phone #", "LOC Building", "LOC Room", "LOC Floor","Country","NPA-NXX","NPA-NXX Split Indicator"};

    public static final String USER_INPUT_LOC_INFO[] = {"Qualification ID/Name", "Location ID/Name","LOC Address", "LOC City", "LOC State",
            "LOC ZIP", "Phone #","LOC Building", "LOC Room", "LOC Floor"};

    public static final String ASE_EPLS_OEW_MIS_AVPN_PRODUCT_AGNOSTIC_LOC_INFO[] = {"In AT&T Footprint at Cust Prem?",
            "FTTB CPL Code","FTTB Project Code","EMUX Type", "WIFI","Small Cell","IWRC"};

    public static final String ASE_PRODUCT_SPECIFIC_INFO[] = {"ASE Availability","Available Bandwidth(s)", "Available Class Of Service","Available Port Type",
            "Build-to-Order Speed","Build-to-Order Class Of Service", "Build-to-Order Port Type","LATA Code", "Location CLLI", "ATT / ICO SWC CLLI",
            "In Region Indicator", "IPAGCLLI", "Fiber Availability", "Fiber Construction","Lightspeed Available","Copper Available","SWCStatus",
            "ICO Company Name","Error Code","Error Text","ADTRANCLLI","Req Bandwidth # of Pairs","Remote terminal","TA5K IPAG CLLI",
            "# of Copper Repeaters","Loop Length(kf)","Expiration Date"};

    public static final String ASE_PRODUCT_COLUMNS[] = {"LATA Code", "Location CLLI", "ATT / ICO SWC CLLI", "In Region Indicator", "IPAGCLLI",
            "Fiber Availability", "Fiber Construction","Lightspeed Available","Copper Available","SWCStatus","ICO Company Name","Error Code","Error Text"};

    public static final String AVAILABLE_CLASS_OF_SERVICE_COLS[] = {"ASE Availability","Available Bandwidth(s)","User Selected Bandwidth(s)", "Available Class Of Service","Available Port Type"};

    public static final String BUILD_TO_ORDER_CLASS_OF_SERVICE_COLS[] = {"Build-to-Order Speed","Build-to-Order Class Of Service", "Build-to-Order Port Type"};

    public static final String EoCU_OF_SERVICE_COLS[] = {"ADTRANCLLI","Req Bandwidth # of Pairs","Remote terminal","TA5K IPAG CLLI","# of Copper Repeaters","Loop Length(kf)"};

    public static final String PRODUCT_IPFROTT_AVAILABLE_HEADER = "IPFR-OTT Available";

    public static final String PRODUCT_IPFLEXREACH_AVAILABLE_HEADER = "IP Flexible Reach Available";

    public static final String BVOIP_PRODUCT_SPECIFIC_INFO[] = {"IP Flexible Reach Available","IP Toll-Free Available",
            "VoIP Centrex Available (aka VDNA)","BAN#", "TN-Rate Center","Addr-Rate Center",
            "ATT Telephone Numbers Available","VoIP e911 Available","Virtual TN", "LEC Switch", "LNS Switch","LEC Name","Local Serving Office",
            "RBOC CLLI","RBOC LSO","Expiration Date" };

    public static final String MIS_PRODUCT_SPECIFIC_INFO[] = {"MIS Available","Available Bandwidth(s)", "Ethernet Access Architecture","Ethernet Physical Interface",
            "Ethernet Pricing Available","Expiration Date"};

    public static final String AVPN_PRODUCT_SPECIFIC_INFO[] = {"AVPN Available","Available Bandwidth(s)", "Ethernet Access Architecture","Ethernet Physical Interface",
            "Ethernet Pricing Available","Expiration Date"};

    public static final String EPLS_PRODUCT_SPECIFIC_INFO[] = {"EPLS-WAN Available","Available Bandwidth(s)", "Ethernet Access Architecture","Ethernet Physical Interface",
            "Ethernet Pricing Available","Expiration Date"};

    public static final String OEW_PRODUCT_SPECIFIC_INFO[] = {"OPTE-WAN Available","Available Bandwidth(s)", "Ethernet Access Architecture","Ethernet Physical Interface",
            "Ethernet Pricing Available","Expiration Date"};

    public static final String ANIRA_PRODUCT_SPECIFIC_INFO[] = {"ANIRA Available","Expiration Date"};

    public static final String AVTS_PRODUCT_SPECIFIC_INFO[] = {"AVTS Available","Expiration Date"};

    public static final String PRODUCT_AGNOSTIC_LOC_INFO_OUTPUT_COL_NAME ="Product Agnostic Location Information OUTPUT";

    public static final String PRODUCT_SPECIFIC_LOC_INFO_OUTPUT_COL_NAME ="Product Specific Service Information OUTPUT";

    public static final String USER_OUTPUT_COL_NAME = "User Provided Qualification/Location Information";

    public static final String AVAILABLE_SERVICES_HEADER_COL="Available Services";

    public static final String BUILD_ORDER_SERVICES_HEADER_COL="Build-to-Order Services";

    public static final String EOCU_HEADER_COL="EoCU";

    public static final String PROCESS_TYPE_MANUAL="MANUAL";

    public static final String PROCESS_TYPE_BULK="BULK";

    public static final String QUAL_PRODUCTS[] = {"ASE","BVoIP","MIS","AVPN","EPLS-WAN","OPT-E-WAN","ANIRA","AVTS"};

    public static final int DATA_ROW_INDEX = 2;

    public static final String LOCATION_SUMMARY_HEADERS[] = {"Qualification ID / Name", "Location ID /  Name","LOC Address",
            "LOC City", "LOC State", "LOC ZIP", "LOC Building", "LOC Floor","LOC Room","LOC Phone"};

    public static final String UNDER_INVESTIGATE_LOCATION_SUMMARY_HEADERS[] = {"Qualification ID / Name", "Location ID / Name",
            "LOC Address", "LOC City", "LOC State", "LOC ZIP", "LOC Building", "LOC Room","LOC Floor","LOC Phone"};

    public static final String UNDER_INVESTIGATE_LOCATION_HEADERS[] = {"Location ID / Name",
            "LOC Address", "LOC City", "LOC State", "LOC ZIP", "LOC Building", "LOC Room","LOC Floor","LOC Phone"};

    public static final String LOCATION_INFORAMTION_HEADER="Location Information";

    public static final String SERVICE_VALIDATION_LOCATION_INFORAMTION_HEADER="Location Information - Requested Address";

    public static final String SERVICE_VALIDATION_SUMMARY_LOCATION_INFORAMTION_HEADER="Location Information - Validated Address";

    public static final String PRODUCT_AVAILABILITY_HEADER = "Product Availability";

    public static final String ASAP_ADOPT = "AsapAdoptCall";
    /**
     * Product Names
     */

    public static final String ASE_PRODUCT = "ASE";

    public static final String BVOIP_PRODUCT = "BVoIP";

    public static final String MIS_PRODUCT = "MIS";

    public static final String AVPN_PRODUCT = "AVPN";

    public static final String EPLS_PRODUCT = "EPLS-WAN";

    public static final String OEW_PRODUCT = "OPT-E-WAN";

    public static final String ANIRA_PRODUCT = "ANIRA";

    public static final String AVTS_PRODUCT = "AVTS";
    // 1607 IT3

    public static final String MIS_EXPRESS_PRODUCT="MIS Express";

    /**
     * Address validation constants
     */

    public static final String NO_MATCH_FOUND = "NO_MATCH";

    public static final String EXACT_MATCH_FOUND = "LOCATION_MATCH";

    public static final String ALERTNATE_MATCH_FOUND = "SIMILAR_MATCH";

    /**
     * Service Groups
     */
    public static final String AVAILABLE_SERVICE = "Available Services";

    public static final String BUILD_TO_ORDER = "Build-to-Order Services";

    public static final String BUILD_TO_ORDER_TEXT = "Build To Order - ";

    public static final String CSI_RES_TXT_INVALID_LOC_NAME_WITH_SUGGESTION = "SIMILAR_MATCH"; // "LOCATION-WITH-SUGGESTION";

    public static final String CSI_RES_TXT_INVALID_LOC_NAME_WITH_NOMATCH = "NO_MATCH"; // "LOCATION-WITH-NO-MATCH";

    public static final String CSI_RES_TXT_LOC_NAME_WITH_EXACTMATCH = "MATCH";

    public static final String CSI_RES_TXT_LOC_NAME_WITH_LOCATIONMATCH = "LOCATION_MATCH";

    public static final String CSI_RES_TXT_LOC_NAME_WITH_STREETMATCH = "STREET_MATCH";

    public static final String CSI_RES_TXT_LOC_NAME_WITH_RANGEDMATCH = "RANGED_MATCH";// added for 1609 Release

    public static final String CSI_RES_STATUS_INVALID_ADDRESS = "Invalid";

    public static final String CSI_RES_STATUS_SERVICE_AVAILABLE = "Service-Available";

    public static final String CSI_REQ_STATUS_NEW = "new";

    public static final String CSI_REQ_STATUS_PROCESSING = "processing";

    public static final String CSI_REQ_STATUS_SUCCESS = "success";

    public static final String CSI_REQ_STATUS_FAIL = "failure";

    public static final String PRODUCT_AVAILABILITY_SUFFIX = "AVAILABILITY";

    public static final String PRODUCT_IP_FLEX_LOCAL = "IP Flex Local";

    public static final String PRODUCT_VDNA = "VDNA";

    public static final String PRODUCT_IP_FLEX_LD = "IP Flex LD";

    public static final String PRODUCT_IP_TOLL_FREE = "IP Toll Free";

    public static final String PRODUCT_IP_FLEXIBLE_REACH = "IP Flexible Reach"; // added for 1609 product

    public static final String PRODUCT_IPFR_OTT = "IPFR-OTT"; // added for 1707 product

    public static final String PRODUCT_ANIRA = "ANIRA";

    public static final String PRODUCT_ASE = "ASE";

    public static final String PRODUCT_OPT_E_WAN = "OPT-E-WAN";

    public static final String PRODUCT_EPLS_WAN = "EPLS-WAN";

    public static final String PRODUCT_AVTS = "AVTS";

    public static final String PRODUCT_AVPN = "AVPN";

    public static final String PRODUCT_MIS = "MIS";

    public static final String PRODUCT_ASE_TYPE = "SDN";

    public static final String PRODUCT_AGNOSTIC_LOC_INFO = "LOCATION_INFO";

    public static final String PRODUCT_BVOIP = "BVoIP";

    public static final String PRODUCT_ADIVB = "ADIVB";

    public static final String PRODUCT_MIS_EXPRESS_IP = "MIS-EXPRESS-IP";

    public static final String GIS = "GIS";

    public static final String ATT_SAG = "AttSag";

    public static final String LEC = "LEC";

    public static final String YES = "YES";

    public static final String NO = "NO";

    public static final String TRUE = "TRUE";

    public static final String AFBR="ASE over Fiber";

    public static final String AVAIL="Available";

    public static final String NOT_AVAIL="Not Available";

    public static final String ERROR="Error";

    public static final String ALL="All";

    public static final String SUCCESS= "success";

    public static final String CURRENTLY_AVAILABLE_SPEED = "CA";

    public static final String BUILD_TO_ORDER_SPEED = "BO";

    public static final String SPEED_GIGA_BITS_PER_SECOND_UNIT = "G";

    public static final String SPEED_MEGA_BITS_PER_SECOND_UNIT = "M";

    public static final String ALS = "ASE over LS (Cu)";

    public static final String EDITFLAG="Y";

    public static final String EDITFLAG_N="N";

    public static final String STATUS_SAVED="Saved";

    public static final String STATUS_PROCESSING="Processing";

    public static final String STATUS_REPROCESSING="Re-Processing";

    public static final String STATUS_QUALIFICATION_COMPLETE="Qualification Complete";

    public static final String STATUS_QUALIFICATION_COMPLETE_REVIEW_REQD="Qualification Complete Review Required";

    public static final String STATUS_SYSTEM_ERROR ="System Error";

    public static final String STATUS_DELETE = "Delete";

    public static final String STATUS_EXPIRED="Expired";

    public static final String PROP_PRODUCT_NAME = "prodName";

    public static final String PROP_SERVICE_AVAILABILITY = "serviceAvailability";

    public static final String PROP_STREET_NAME = "streetName";

    public static final String PROP_SPEED_VAL = "speedVal";
    public static final String PROP_LOC_NAME = "locName";

	/*1607 New Products Start*/

    public static final String PRODUCT_MIS_EXPRESS = "MIS-Express";

    public static final String PRODUCT_AVPN_EXPRESS = "AVPN-Express";

    public static final String PRODUCT_VVB_EXPRESS = "VVB-Express";

    public static final String PRODUCT_ADSL = "ADSL";

    public static final String PRODUCT_HSIAE = "HSIAE";

    public static final String PRODUCT_UBV = "UVERSE-Voice";

    public static final String PRODUCT_IP_BROADBAND = "IPBB";

    public static final String PRODUCT_OFFICE_AT_HAND = "Office at Hand";

    public static final String PRODUCT_NETWORK_BASED_FIREWALL = "Network Based Firewall";

    public static final String PRODUCT_CLOUD_WEB_SECURITY_SERVICES = "Cloud Web Security Services";

	/* 1612 Products Start */

    public static final String PRODUCT_ASE_NOD = "ASE NoD";

    public static final String PRODUCT_MISE_PLUS_IPFLEX = "MISePlusIPFlex";

    public static final String SELL_AND_DEPLOY = "SD";

    public static final String DEPLOY_AND_SELL = "DS";

    public static final String SELL_AND_DEPLOY_STR = "Sell & Deploy";

    public static final String DEPLOY_AND_SELL_STR = "Deploy & Sell";
    public static final String NOT_AVAILABLE_STR = "Not Available";

    public static final String ASE_NOD_PRODUCT_SPECIFIC_INFO[] = {"ASE NoD Available", "Service Option", "Available CIR" , "Max CIR" ,"Expiration Date"};

    public static final String ASE_NOD_SELL_AND_DEPLOY_CIR = "Sell & Deploy Max CIR 1Gbps";

    public static final String ASE_NOD_DEPLOY_AND_SELL_OPTICAL_CIR = "Deploy & Sell Optical Max CIR";

    public static final String ASE_NOD_DEPLOY_AND_SELL_ELECTRICAL_CIR = "Deploy & Sell Electrical Max CIR";

    public static final String ASE_NOD_DEPLOY_AND_SELL_ELECTRICAL_FILTERED_SPEED = "Deploy & '||'Sell Electrical Max CIR";

    public static final String ASE_NOD_DEPLOY_AND_SELL_OPTICAL_FILTERED_SPEED = "Deploy & '||'Sell Optical Max CIR";

    public static final String NEWLINE = System.getProperty("line.separator");

    public static final String FTTB_TIER_1 = "Tier 1";

    public static final String FTTB_TIER_2 = "Tier 2";

    public static final String FTTB_TIER_3 = "Tier 3";

    public static final String FTTB_TIER_4 = "Tier 4";

    public static final String MIS_E_PLUS_IP_FLEX_PRODUCT_SPECIFIC_INFO[] = {"MIS Express w Flex Bundle Available","MIS Express Available",
            "In AT&T Footprint at Cust Prem","FTTB Tier", "Available Bandwidth(s)","Available Ethernet Access Architecture","IP Flexible Reach Available",
            "BAN#", "TN-Rate Center","Addr-Rate Center","ATT Telephone Numbers Available","VoIP e911 Available","Virtual TN", "LEC Switch",
            "LNS Switch","LEC Name","Local Serving Office","RBOC CLLI","RBOC LSO","Expiration Date"};

    public static final String MIS_E_PLUS_IP_FLEX_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"MIS Express w Flex Bundle Available","MIS Express Available",
            "In AT&T Footprint at Cust Prem","FTTB Tier", "Available Bandwidth(s)","User Selected Bandwidth(s)","Available Ethernet Access Architecture",
            "IP Flexible Reach Available","BAN#", "TN-Rate Center","Addr-Rate Center","ATT Telephone Numbers Available","VoIP e911 Available",
            "Virtual TN", "LEC Switch", "LNS Switch","LEC Name","Local Serving Office","RBOC CLLI","RBOC LSO","Expiration Date"};

	/* 1607 New Products - Product Specific Service Information Columns */

    public static final String MIS_EXPRESS_PRODUCT_SPECIFIC_INFO[] = {"MIS Express Available","Network On Demand Available","In AT&T Footprint at Cust Prem?","FTTB Tier",
            "Available Bandwidth(s)","Available Architecture","Expiration Date"};

    public static final String VVB_EXPRESS_PRODUCT_SPECIFIC_INFO[] = {"VVB Express Available","Available Bandwidth(s)", "Ethernet Access Architecture",
            "Ethernet Physical Interface","Expiration Date"};

    public static final String AVPN_EXPRESS_PRODUCT_SPECIFIC_INFO[] = {"AVPN Express Available","Available Bandwidth(s)", "Ethernet Access Architecture",
            "Ethernet Physical Interface","Expiration Date"};

    public static final String DSL_BROADBAND_PRODUCT_SPECIFIC_INFO[] = {"AT&T DSL - Small Business Available",
            "Available Up to Downstream/Upstream Bandwidth(s)","Expiration Date"};

    public static final String HSI_BROADBAND_PRODUCT_SPECIFIC_INFO[] = {"AT&T High Speed Internet -  Enterprise Available",
            "Broadband II Downstream/Upstream Available Bandwidth(s)",
            "Broadband Up to  Downstream/Upstream Available Bandwidth(s)",
            "ADSL In-Region Up to Downstream/Upstream Available Bandwidth(s)",
            "ADSL In-Region - Direct Up to Downstream/Upstream Available Bandwidth(s)",
            "ADSL Out of Region Up to Downstream/Upstream Available Bandwidth(s)",
            "SDSL Out of Region Up to Downstream/Upstream Available Bandwidth(s)",
            "Network Transport Type","Expiration Date"};

    public static final String UBV_PRODUCT_SPECIFIC_INFO[] = {"Uverse Business Voice Available","Emux Available","Expiration Date"};

    public static final String IP_BROADBAND_EXPRESS_PRODUCT_SPECIFIC_INFO[] = {"AT&T IP Broadband - Small Business Available",
            "ABF Downstream/Upstream Available Bandwidth(s)",
            "GPON Downstream/Upstream Available Bandwidth(s)",
            "VDSL  Up to Downstream/Upstream Available Bandwidth(s)",
            "IPDSL Up to Downstream/Upstream Available Bandwidth(s)",
            "Emux Available?","Expiration Date"};

    public static final String OFFICE_AT_HAND_PRODUCT_SPECIFIC_INFO[] =  {"Office @ Hand Available","Expiration Date"};

    public static final String NETWORK_BASED_FIREWALL_PRODUCT_SPECIFIC_INFO[] = {"Network Based Firewall Available","Expiration Date"};

    public static final String CLOUD_WEB_SECURITY_SERVICE_PRODUCT_SPECIFIC_INFO[] = {"Cloud Web Security Services Available","Expiration Date"};

    public static final String ASYNC_PROCESS_METHOD = "ASYNC";

    public static final String SYNC_PROCESS_METHOD = "SYNC";

    public static final String FLAG_Y = "Y";

    public static final String FLAG_N = "N";

    public static final String FLAG_E="E";

    public static final String SITE_REGION_O = "O";

    public static final String STANDARDIZED_FLAG= "S";

    public static final String NOT_STANDARDIZED_FLAG= "N";

    public static final String ASC = "ASE over Copper (EoCu)";

    public static final String NETWORK_TRANSPORT_TYPE_FTTBC = "FTTB-C";

    public static final String NETWORK_TRANSPORT_TYPE_FTTBF = "FTTB-F";

    public static final String ADSL_HSI_LINESHARE_DIRECT = "ADSL HSI Lineshare/Direct (POTS)";

    public static final String ADSL_FA_LINESHARE_DIRECT = "ADSL FastAccess Lineshare/Direct (POTS)";

    public static final String ABF = "ABF";

    public static final String GIGAPOWER = "GigaPower";

    public static final String VDSL = "VDSL";

    public static final String IPDSL = "IPDSL";

    public static final String GPON = "GPON";

    public static final String BROADBAND_II = "Broadband II";

    public static final String BROADBAND = "Broadband";

    public static final String ADSL_IN_REGION = "ADSL In Region";

    public static final String ADSL_IN_REGION_DIRECT = "ADSL In Region - Direct";

    public static final String ADSL_OUT_OF_REGION = "ADSL Out of Region";

    public static final String SDSL_OUT_OF_REGION = "SDSL Out of Region";

    public static final  String INTEGRATION_EXCEL = "Integration";

    public static final  String SERVICE_VALIDATION_RESULTS_EXCEL = "ServiceValidationResults";

    public static final String BROADBAND_DISPLAY_ORDER_SERVICE_TYPES[] = {"IPBB_SPEED_ORDER","IPBB_SPEED_ORDER_NEW","HSIE_SPEED_ORDER","DSL_SB_SPEED_ORDER"};

    // Integration Export Excel

    public static final String ASE_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"ASE Availability","Available Bandwidth(s) (Mbps)",
            "User Selected Bandwidth(s) (Mbps)","Available Class Of Service","Available Port Type",
            "Build-to-Order Speed","Build-to-Order Class Of Service", "Build-to-Order Port Type","LATA Code",
            "Location CLLI", "ATT / ICO SWC CLLI", "In Region Indicator", "IPAGCLLI",
            "Fiber Availability", "Fiber Construction","Lightspeed Available","Copper Available","SWCStatus",
            "ICO Company Name","Error Code","Error Text","ADTRANCLLI","Req Bandwidth # of Pairs",
            "Remote terminal","TA5K IPAG CLLI","# of Copper Repeaters","Loop Length(kf)","Expiration Date"};

    public static final String MIS_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"MIS Available","Available Bandwidth(s) (Mbps)",
            "User Selected Bandwidth(s) (Mbps)","Ethernet Access Architecture","Ethernet Physical Interface",
            "Ethernet Pricing Available","Expiration Date"};

    public static final String AVPN_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"AVPN Available","Available Bandwidth(s) (Mbps)",
            "User Selected Bandwidth(s) (Mbps)","Ethernet Access Architecture","Ethernet Physical Interface",
            "Ethernet Pricing Available","Expiration Date"};

    public static final String EPLS_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"EPLS-WAN Available","Available Bandwidth(s) (Mbps)",
            "User Selected Bandwidth(s) (Mbps)","Ethernet Access Architecture","Ethernet Physical Interface",
            "Ethernet Pricing Available","Expiration Date"};

    public static final String OEW_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"OPTE-WAN Available","Available Bandwidth(s) (Mbps)",
            "User Selected Bandwidth(s) (Mbps)","Ethernet Access Architecture","Ethernet Physical Interface",
            "Ethernet Pricing Available","Expiration Date"};

    public static final String MIS_EXPRESS_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"MIS Express Available","Network On Demand Available","In AT&T Footprint at Cust Prem?","FTTB Tier",
            "Available Bandwidth(s) (Mbps)","User Selected Bandwidth(s) (Mbps)","Available Architecture","Expiration Date"};

    public static final String VVB_EXPRESS_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"VVB Express Available","Available Bandwidth(s) (Mbps)",
            "User Selected Bandwidth(s) (Mbps)","Ethernet Access Architecture",
            "Ethernet Physical Interface","Expiration Date"};

    public static final String AVPN_EXPRESS_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"AVPN Express Available","Available Bandwidth(s) (Mbps)",
            "User Selected Bandwidth(s) (Mbps)","Ethernet Access Architecture",
            "Ethernet Physical Interface","Expiration Date"};
    public static final String DSL_BROADBAND_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"AT&T DSL - Small Business Available",
            "Available Up to Downstream/Upstream Bandwidth(s)",
            "User Selected Up to Downstream/Upstream Bandwidth(s)",
            "Expiration Date"};

    public static final String HSI_BROADBAND_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"AT&T High Speed Internet -  Enterprise Available",
            "Broadband II Downstream/Upstream Available Bandwidth(s)",
            "Broadband II User Selected Downstream/Upstream Available Bandwidth(s)",
            "Broadband Up to  Downstream/Upstream Available Bandwidth(s)",
            "Broadband User Selected Up to Downstream/Upstream Available Bandwidth(s)",
            "ADSL In-Region Up to Downstream/Upstream Available Bandwidth(s)",
            "ADSL In-Region User Selected Up to Downstream/Upstream Available Bandwidth(s)",
            "ADSL In-Region - Direct Up to Downstream/Upstream Available Bandwidth(s)",
            "ADSL In-Region - Direct User Selected Up to Downstream/Upstream Bandwidth(s)",
            "ADSL Out of Region Up to Downstream/Upstream Available Bandwidth(s)",
            "ADSL Out of Region User Selected Up to Downstream/Upstream Bandwidth(s)",
            "SDSL Out of Region Up to Downstream/Upstream Available Bandwidth(s)",
            "SDSL Out of Region User Selected Up to Downstream/Upstream Bandwidth(s)",
            "Network Transport Type","Expiration Date"};

    public static final String IP_BROADBAND_EXPRESS_PRODUCT_SPECIFIC_INFO_INTEGRATION[] = {"AT&T IP Broadband - Small Business Available",
            "ABF Downstream/Upstream Available Bandwidth(s)","ABF User Selected Downstream/Upstream Bandwidth(s)",
            "GPON Downstream/Upstream Available Bandwidth(s)","GPON User Selected Downstream/Upstream Bandwidth(s)",

            "VDSL  Up to Downstream/Upstream Available Bandwidth(s)","VDSL User Selected Up to Downstream/Upstream Bandwidth(s)",
            "IPDSL Up to Downstream/Upstream Available Bandwidth(s)","IPDSL User Selected Up to Downstream/Upstream Bandwidth(s)",
            "Emux Available?","Expiration Date"};

    public static final String PRODUCT_OPTIONS = "product-options";

    public static final String LOCATION_INFORMATION = "location-information";

    public static final String PRODUCT_SELECTIONS = "product-selections";

    public static final String NOT_FOUND = "not-found";

    public static final String ALTERNATE_FOUND = "alternate-found";

    public static final String MATCH_FOUND = "match-found";

    public static final String UNDER_INVESTIGATION = "under-investigation";

    public static final String EXISTING_QUALIFICATION = "existing-qualification";

    public static final String PRODUCT_INACTIVE_BANNER = "product-inactive-banner";

    // Added for 1609 Release Start

    public static final String GIS_ONLY_CSI_LOCATION_ELEMENT = "GIS";

    public static final String SAG_ONLY_CSI_LOCATION_ELEMENT = "SAG";

    public static final String SAG_GIS_CSI_LOCATION_ELEMENT = "SAG-GIS";

    public static final String GIS_SAG_CSI_LOCATION_ELEMENT = "GIS-SAG";

    public static final String LEC_SAG_CSI_LOCATION_ELEMENT = "LSAG";

    // Added for 1609 Release Finish

    // Added for SSDF Error Handling Start

    public static final String CSI_RESP_SAG_NOT_FOUND = "SAG_NOT_FOUND";

    public static final String CSI_RESP_GIS_NOT_FOUND = "GIS_NOT_FOUND";

    public static final String CSI_RESP_NOT_FOUND = "NOT_FOUND";

    public static final String CSI_RESP_QUALIFIED_PRODUCTS_NOT_FOUND = "QUAL_PRODUCTS_NOT_FOUND";

    public static final String CSI_RESP_NO_MATCH = "CSI RESPONSE";

    public static final String UNKNOWN_ERROR_NO_MATCH = "UNKNOWN EXCEPTION";

    public static final String CSI_RESP_GIS_ERROR_NO_MATCH = "GIS LOCATION ELEMENT";

    public static final String CSI_RESP_SAG_ERROR_NO_MATCH = "SAG LOCATION ELEMENT";

    public static final String CSI_RESP_QUALIFIED_PRODS_ERROR_NO_MATCH = "QUALIFIED PRODUCTS ELEMENT";

    public static final String QUAL_CLIENT_NAME  = "SE-QUAL-TOOL";

    public static final String CIR_250M = "250M";
    public static final String CIR_1G = "1G";
    public static final String CIR_10G = "10G";
    public static final String CIR_100G = "100G";

    public static final String SYSTEM_ERROR_CODE = "000";

    public static final String SAVED_CODE = "001";

    public static final String QC_CODE = "003";

    public static final String QC_RR_CODE = "004";

    public static final String PROCESSING_CODE = "002";

    public static final String NO_MATCH_FLAG_CSI = "CSI";

    public static final String NO_MATCH_FLAG_ERROR = "ERROR";

    // Added for SSDF Error Handling Finish

    public static final String PRODUCT_DDOS_DEFENSE = "DDos Defense";

    public static final String PRODUCT_ATT_PROXY_SERVICES = "ATT Proxy Services";

    public static final String PRODUCT_ATT_IDP_HOST_BASED = "ATT IDP Host - Based";

    public static final String PRODUCT_ATT_IDP_SERVICES = "ATT IDP Services";

    public static final String PRODUCT_ATT_PREMISES_FIREWALL = "ATT Premises-Based Firewall";

    public static final String PRODUCT_ATT_WIFI = "ATT WIFI";

    public static final String PRODUCT_ATT_FLEXWARE = "flexware";

    public static final String PRODUCT_ATT_COLLABORATE = "ATT Collaborate";

    public static final String ATT_COLLABORATE_PRODUCT_SPECIFIC_INFO[] = {"AT&T Collaborate Available ","AT&T Telephone Numbers Available","Expiration Date"};

    public static final String DDOS_DEFENSE_PRODUCT_SPECIFIC_INFO[] = {"DDOS Defense Available ","Expiration Date"};

    public static final String ATT_PROXY_SERVICES_PRODUCT_SPECIFIC_INFO[] = {"AT&T Proxy Services Available ","Expiration Date"};

    public static final String ATT_IDP_HOST_BASED_PRODUCT_SPECIFIC_INFO[] = {"AT&T ID/P Host-Based Available ","Expiration Date"};

    public static final String ATT_IDP_SERVICES_PRODUCT_SPECIFIC_INFO[] = {"AT&T ID/P Services Available ","Expiration Date"};

    public static final String ATT_PREMISES_FIREWALL_PRODUCT_SPECIFIC_INFO[] = {"AT&T Premise Based Firewall Available ","Expiration Date"};

    public static final String ATT_WIFI_PRODUCT_SPECIFIC_INFO[] = {"AT&T WiFi Available ","Expiration Date"};

    public static final String PROFESSIONAL_SECURITY_SERVICES = "Professional & Security Services";

    public static final String BUILDOUT_REQUIRED = "Build Out Required";

    public static final String FIBER_MAX = "Fiber:  1 Gbps";

    public static final String ELECTRICAL_MAX = "Electrical: 1 Gbps";

    public static final String SITE_VISIT_REQUIRED = "Site Visit Required";

    public static final String SSDFQUAL_ADDR_RES= "SSDFQUAL-ADDR-RES";

    public static final String SAG_HOST_STATUS =  "SAG-HOST-STATUS";

    public static final String SAG_DISPLAY_STATUS =  "SAG-DISPLAY-STATUS";

    public static final String HOST_NAME = "HOST_NAME";

    public static final String MATCH_STATUS = "MATCH_STATUS";

    public static final String MOST_OF_WORLD = "MostOfWorld";
}
