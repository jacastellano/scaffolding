
export interface Entity {
    entityId?: number;
    entityCode: string;
    entityTitle: string;
    entityDescription?: string;
    entityTypeId?: number;
    createDate?: String;
    createUser?: number;
    updateDate?: String;
    updateUser?: number;
}

export const convertToEntity = (data: any): Entity => {

    const createDate = new Date(data.createDate);
    const updateDate = new Date(data.updateDate);

    const entity: Entity = {
      ...data,
      createDate: createDate.toISOString(),
      updateDate: updateDate.toISOString(),
    };

    return entity;
  }
