export interface Character {
  id: number;
  type: string;
  links: {
    self: string;
  };
  attributes: {
    canonicalName: string;
    description: string;
    image: {
      original: string;
    };
  };
}
