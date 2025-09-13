package ir.mohaymen.starpack.wrapper.core.bdmp.get_warehouses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * نماینده‌ی یک آیتم از آرایه‌ی خروجی API.
 * اگر می‌خواهی کل پاسخ آرایه‌ای را در یک آبجکت داشته باشی، یک کلاس
 * WarehousesResponse extends ArrayList<DataEntity> بساز (نمونه در پایین آمده است).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetWarehousesEntityType {

    @JsonProperty("IsVirtual")
    private Boolean isVirtual;

    @JsonProperty("ParentId")
    private Integer parentId;

    @JsonProperty("Columns")
    private List<Column> columns;

    @JsonProperty("BinaryStorageType")
    private Integer binaryStorageType;

    @JsonProperty("DatabaseTypes")
    private List<Integer> databaseTypes;

    @JsonProperty("ParentColumns")
    private Object parentColumns;

    @JsonProperty("Map")
    private Object map;

    @JsonProperty("Detail")
    private Detail detail;

    @JsonProperty("DefaultFilter")
    private Object defaultFilter;

    @JsonProperty("CreateEntityWithNoAttributes")
    private Boolean createEntityWithNoAttributes;

    @JsonProperty("CreateLinkWithNoAttributes")
    private Boolean createLinkWithNoAttributes;

    @JsonProperty("RowLevelAccessConfiguration")
    private Object rowLevelAccessConfiguration;

    @JsonProperty("PostProcessSettings")
    private List<Object> postProcessSettings;

    @JsonProperty("WarehouseUsageType")
    private Integer warehouseUsageType;

    @JsonProperty("ID")
    private Integer id;

    @JsonProperty("IconUrl")
    private String iconUrl;

    @JsonProperty("Icon")
    private String icon;

    @JsonProperty("Bytes")
    private Integer bytes;

    @JsonProperty("FolderID")
    private Integer folderID;

    @JsonProperty("ContentSize")
    private Integer contentSize;

    @JsonProperty("Version")
    private Integer version;

    @JsonProperty("CanEdit")
    private Boolean canEdit;

    @JsonProperty("CreateDate")
    private String createDate; // می‌توان به OffsetDateTime هم نگاشت کرد

    @JsonProperty("CreatorUserId")
    private Integer creatorUserId;

    @JsonProperty("CreatorIP")
    private String creatorIP;

    @JsonProperty("CreatorUserName")
    private String creatorUserName;

    @JsonProperty("LastModifiedDate")
    private String lastModifiedDate;

    @JsonProperty("LastModifireUserId")
    private Integer lastModifireUserId;

    @JsonProperty("LastModifireUserName")
    private String lastModifireUserName;

    @JsonProperty("ModifierIP")
    private String modifierIP;

    @JsonProperty("DataType")
    private Integer dataType;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("RoleBasedAccess")
    private List<RoleBasedAccess> roleBasedAccess;

    @JsonProperty("TagBasedAccess")
    private List<Object> tagBasedAccess;

    // ======== Nested Types ========

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Column {
        @JsonProperty("Id") private Integer id;
        @JsonProperty("Name") private String name;
        @JsonProperty("Type") private TypeDescriptor type;
        @JsonProperty("IsSimorghId") private Boolean isSimorghId;
        @JsonProperty("Order") private Integer order;
        @JsonProperty("IsVisible") private Boolean isVisible;
        @JsonProperty("Description") private String description;
        @JsonProperty("ColumnDetail") private ColumnDetail columnDetail;
        @JsonProperty("IsPreparationDateTime") private Boolean isPreparationDateTime;
        @JsonProperty("IsPreparationDescription") private Boolean isPreparationDescription;
        @JsonProperty("IsExternalWarehouseCreationDateTime") private Boolean isExternalWarehouseCreationDateTime;

        // getters & setters
        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public TypeDescriptor getType() { return type; }
        public void setType(TypeDescriptor type) { this.type = type; }
        public Boolean getSimorghId() { return isSimorghId; }
        public void setSimorghId(Boolean simorghId) { isSimorghId = simorghId; }
        public Integer getOrder() { return order; }
        public void setOrder(Integer order) { this.order = order; }
        public Boolean getVisible() { return isVisible; }
        public void setVisible(Boolean visible) { isVisible = visible; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public ColumnDetail getColumnDetail() { return columnDetail; }
        public void setColumnDetail(ColumnDetail columnDetail) { this.columnDetail = columnDetail; }
        public Boolean getPreparationDateTime() { return isPreparationDateTime; }
        public void setPreparationDateTime(Boolean preparationDateTime) { isPreparationDateTime = preparationDateTime; }
        public Boolean getPreparationDescription() { return isPreparationDescription; }
        public void setPreparationDescription(Boolean preparationDescription) { isPreparationDescription = preparationDescription; }
        public Boolean getExternalWarehouseCreationDateTime() { return isExternalWarehouseCreationDateTime; }
        public void setExternalWarehouseCreationDateTime(Boolean externalWarehouseCreationDateTime) { isExternalWarehouseCreationDateTime = externalWarehouseCreationDateTime; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TypeDescriptor {
        @JsonProperty("Type") private Integer type;
        @JsonProperty("Title") private String title;

        public Integer getType() { return type; }
        public void setType(Integer type) { this.type = type; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ColumnDetail {
        @JsonProperty("ExternalElasticFieldDetail") private Object externalElasticFieldDetail;
        @JsonProperty("InternalElasticFieldDetail") private Object internalElasticFieldDetail;
        @JsonProperty("ExternalOracleColumnDetail") private ExternalOracleColumnDetail externalOracleColumnDetail;
        @JsonProperty("InternalOracleColumnDetail") private InternalOracleColumnDetail internalOracleColumnDetail;

        public Object getExternalElasticFieldDetail() { return externalElasticFieldDetail; }
        public void setExternalElasticFieldDetail(Object externalElasticFieldDetail) { this.externalElasticFieldDetail = externalElasticFieldDetail; }
        public Object getInternalElasticFieldDetail() { return internalElasticFieldDetail; }
        public void setInternalElasticFieldDetail(Object internalElasticFieldDetail) { this.internalElasticFieldDetail = internalElasticFieldDetail; }
        public ExternalOracleColumnDetail getExternalOracleColumnDetail() { return externalOracleColumnDetail; }
        public void setExternalOracleColumnDetail(ExternalOracleColumnDetail externalOracleColumnDetail) { this.externalOracleColumnDetail = externalOracleColumnDetail; }
        public InternalOracleColumnDetail getInternalOracleColumnDetail() { return internalOracleColumnDetail; }
        public void setInternalOracleColumnDetail(InternalOracleColumnDetail internalOracleColumnDetail) { this.internalOracleColumnDetail = internalOracleColumnDetail; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class InternalOracleColumnDetail {
        @JsonProperty("NameInDatabase") private String nameInDatabase;
        @JsonProperty("Length") private Integer length;
        @JsonProperty("DecimalPlaces") private Integer decimalPlaces;

        public String getNameInDatabase() { return nameInDatabase; }
        public void setNameInDatabase(String nameInDatabase) { this.nameInDatabase = nameInDatabase; }
        public Integer getLength() { return length; }
        public void setLength(Integer length) { this.length = length; }
        public Integer getDecimalPlaces() { return decimalPlaces; }
        public void setDecimalPlaces(Integer decimalPlaces) { this.decimalPlaces = decimalPlaces; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExternalOracleColumnDetail {
        @JsonProperty("NameInDatabase") private String nameInDatabase;
        @JsonProperty("NeedCleaning") private Boolean needCleaning;
        @JsonProperty("IsNGram") private Boolean isNGram;
        @JsonProperty("IsQueryable") private Boolean isQueryable;

        public String getNameInDatabase() { return nameInDatabase; }
        public void setNameInDatabase(String nameInDatabase) { this.nameInDatabase = nameInDatabase; }
        public Boolean getNeedCleaning() { return needCleaning; }
        public void setNeedCleaning(Boolean needCleaning) { this.needCleaning = needCleaning; }
        public Boolean getNGram() { return isNGram; }
        public void setNGram(Boolean NGram) { isNGram = NGram; }
        public Boolean getQueryable() { return isQueryable; }
        public void setQueryable(Boolean queryable) { isQueryable = queryable; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Detail {
        @JsonProperty("InternalOracleDetail") private InternalOracleDetail internalOracleDetail;
        @JsonProperty("ExternalOracleDetail") private ExternalOracleDetail externalOracleDetail;
        @JsonProperty("InternalElasticDetail") private Object internalElasticDetail;
        @JsonProperty("ExternalElasticDetail") private Object externalElasticDetail;

        public InternalOracleDetail getInternalOracleDetail() { return internalOracleDetail; }
        public void setInternalOracleDetail(InternalOracleDetail internalOracleDetail) { this.internalOracleDetail = internalOracleDetail; }
        public ExternalOracleDetail getExternalOracleDetail() { return externalOracleDetail; }
        public void setExternalOracleDetail(ExternalOracleDetail externalOracleDetail) { this.externalOracleDetail = externalOracleDetail; }
        public Object getInternalElasticDetail() { return internalElasticDetail; }
        public void setInternalElasticDetail(Object internalElasticDetail) { this.internalElasticDetail = internalElasticDetail; }
        public Object getExternalElasticDetail() { return externalElasticDetail; }
        public void setExternalElasticDetail(Object externalElasticDetail) { this.externalElasticDetail = externalElasticDetail; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class InternalOracleDetail {
        @JsonProperty("TableName") private String tableName;

        public String getTableName() { return tableName; }
        public void setTableName(String tableName) { this.tableName = tableName; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExternalOracleDetail {
        @JsonProperty("ConnectionInfos") private Object connectionInfos;
        @JsonProperty("ConnectionAttributes") private ConnectionAttributes connectionAttributes;
        @JsonProperty("TableName") private String tableName;
        @JsonProperty("UseAccessibleConnections") private Boolean useAccessibleConnections;

        public Object getConnectionInfos() { return connectionInfos; }
        public void setConnectionInfos(Object connectionInfos) { this.connectionInfos = connectionInfos; }
        public ConnectionAttributes getConnectionAttributes() { return connectionAttributes; }
        public void setConnectionAttributes(ConnectionAttributes connectionAttributes) { this.connectionAttributes = connectionAttributes; }
        public String getTableName() { return tableName; }
        public void setTableName(String tableName) { this.tableName = tableName; }
        public Boolean getUseAccessibleConnections() { return useAccessibleConnections; }
        public void setUseAccessibleConnections(Boolean useAccessibleConnections) { this.useAccessibleConnections = useAccessibleConnections; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConnectionAttributes {
        @JsonProperty("SID") private String sid;
        @JsonProperty("ServiceName") private String serviceName;
        @JsonProperty("Url") private String url;
        @JsonProperty("Port") private Integer port;
        @JsonProperty("Username") private String username;
        @JsonProperty("Password") private String password;
        @JsonProperty("HasPassword") private Boolean hasPassword;

        public String getSid() { return sid; }
        public void setSid(String sid) { this.sid = sid; }
        public String getServiceName() { return serviceName; }
        public void setServiceName(String serviceName) { this.serviceName = serviceName; }
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        public Integer getPort() { return port; }
        public void setPort(Integer port) { this.port = port; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public Boolean getHasPassword() { return hasPassword; }
        public void setHasPassword(Boolean hasPassword) { this.hasPassword = hasPassword; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RoleBasedAccess {
        @JsonProperty("RoleID") private Integer roleID;
        @JsonProperty("AccessLevelIDs") private List<Integer> accessLevelIDs;

        public Integer getRoleID() { return roleID; }
        public void setRoleID(Integer roleID) { this.roleID = roleID; }
        public List<Integer> getAccessLevelIDs() { return accessLevelIDs; }
        public void setAccessLevelIDs(List<Integer> accessLevelIDs) { this.accessLevelIDs = accessLevelIDs; }
    }

    // ======== Getters & Setters for DataEntity ========

    public Boolean getVirtual() { return isVirtual; }
    public void setVirtual(Boolean virtual) { isVirtual = virtual; }
    public Integer getParentId() { return parentId; }
    public void setParentId(Integer parentId) { this.parentId = parentId; }
    public List<Column> getColumns() { return columns; }
    public void setColumns(List<Column> columns) { this.columns = columns; }
    public Integer getBinaryStorageType() { return binaryStorageType; }
    public void setBinaryStorageType(Integer binaryStorageType) { this.binaryStorageType = binaryStorageType; }
    public List<Integer> getDatabaseTypes() { return databaseTypes; }
    public void setDatabaseTypes(List<Integer> databaseTypes) { this.databaseTypes = databaseTypes; }
    public Object getParentColumns() { return parentColumns; }
    public void setParentColumns(Object parentColumns) { this.parentColumns = parentColumns; }
    public Object getMap() { return map; }
    public void setMap(Object map) { this.map = map; }
    public Detail getDetail() { return detail; }
    public void setDetail(Detail detail) { this.detail = detail; }
    public Object getDefaultFilter() { return defaultFilter; }
    public void setDefaultFilter(Object defaultFilter) { this.defaultFilter = defaultFilter; }
    public Boolean getCreateEntityWithNoAttributes() { return createEntityWithNoAttributes; }
    public void setCreateEntityWithNoAttributes(Boolean createEntityWithNoAttributes) { this.createEntityWithNoAttributes = createEntityWithNoAttributes; }
    public Boolean getCreateLinkWithNoAttributes() { return createLinkWithNoAttributes; }
    public void setCreateLinkWithNoAttributes(Boolean createLinkWithNoAttributes) { this.createLinkWithNoAttributes = createLinkWithNoAttributes; }
    public Object getRowLevelAccessConfiguration() { return rowLevelAccessConfiguration; }
    public void setRowLevelAccessConfiguration(Object rowLevelAccessConfiguration) { this.rowLevelAccessConfiguration = rowLevelAccessConfiguration; }
    public List<Object> getPostProcessSettings() { return postProcessSettings; }
    public void setPostProcessSettings(List<Object> postProcessSettings) { this.postProcessSettings = postProcessSettings; }
    public Integer getWarehouseUsageType() { return warehouseUsageType; }
    public void setWarehouseUsageType(Integer warehouseUsageType) { this.warehouseUsageType = warehouseUsageType; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public Integer getBytes() { return bytes; }
    public void setBytes(Integer bytes) { this.bytes = bytes; }
    public Integer getFolderID() { return folderID; }
    public void setFolderID(Integer folderID) { this.folderID = folderID; }
    public Integer getContentSize() { return contentSize; }
    public void setContentSize(Integer contentSize) { this.contentSize = contentSize; }
    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }
    public Boolean getCanEdit() { return canEdit; }
    public void setCanEdit(Boolean canEdit) { this.canEdit = canEdit; }
    public String getCreateDate() { return createDate; }
    public void setCreateDate(String createDate) { this.createDate = createDate; }
    public Integer getCreatorUserId() { return creatorUserId; }
    public void setCreatorUserId(Integer creatorUserId) { this.creatorUserId = creatorUserId; }
    public String getCreatorIP() { return creatorIP; }
    public void setCreatorIP(String creatorIP) { this.creatorIP = creatorIP; }
    public String getCreatorUserName() { return creatorUserName; }
    public void setCreatorUserName(String creatorUserName) { this.creatorUserName = creatorUserName; }
    public String getLastModifiedDate() { return lastModifiedDate; }
    public void setLastModifiedDate(String lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }
    public Integer getLastModifireUserId() { return lastModifireUserId; }
    public void setLastModifireUserId(Integer lastModifireUserId) { this.lastModifireUserId = lastModifireUserId; }
    public String getLastModifireUserName() { return lastModifireUserName; }
    public void setLastModifireUserName(String lastModifireUserName) { this.lastModifireUserName = lastModifireUserName; }
    public String getModifierIP() { return modifierIP; }
    public void setModifierIP(String modifierIP) { this.modifierIP = modifierIP; }
    public Integer getDataType() { return dataType; }
    public void setDataType(Integer dataType) { this.dataType = dataType; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<RoleBasedAccess> getRoleBasedAccess() { return roleBasedAccess; }
    public void setRoleBasedAccess(List<RoleBasedAccess> roleBasedAccess) { this.roleBasedAccess = roleBasedAccess; }
    public List<Object> getTagBasedAccess() { return tagBasedAccess; }
    public void setTagBasedAccess(List<Object> tagBasedAccess) { this.tagBasedAccess = tagBasedAccess; }
}
