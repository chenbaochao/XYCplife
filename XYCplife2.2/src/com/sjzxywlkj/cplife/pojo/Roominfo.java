package com.sjzxywlkj.cplife.pojo;

public class Roominfo {
    private String outRoomId;

    private String communityId;

    private String groups;

    private String building;

    private String unit;

    private String room;

    private String address;
    public Roominfo(){}
    public Roominfo(String outRoomId, String communityId, String groups,
			String building, String unit, String room, String address) {
		
		this.outRoomId = outRoomId;
		this.communityId = communityId;
		this.groups = groups;
		this.building = building;
		this.unit = unit;
		this.room = room;
		this.address = address;
	}

	public String getOutRoomId() {
        return outRoomId;
    }

    public void setOutRoomId(String outRoomId) {
        this.outRoomId = outRoomId == null ? null : outRoomId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups == null ? null : groups.trim();
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building == null ? null : building.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}