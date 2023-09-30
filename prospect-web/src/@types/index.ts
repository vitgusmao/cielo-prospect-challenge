export interface ClientInterface {
  id: number;
  createdOn: string;
  updatedOn: string;
  status: 'NOT_PROCESSED' | 'PROCESSING' | 'ACCEPTED' | 'REJECTED';
  mcc: string;
}

export interface NaturalPerson extends ClientInterface {
  cpf: string;
  email: string;
  name: string;
}

export interface LegalEntity extends ClientInterface {
  cnpj: string;
  corporateReason: string;
  mcc: string;
  contactCpf: string;
  contactName: string;
  contactEmail: string;
}

export type ClientType = NaturalPerson | LegalEntity;
