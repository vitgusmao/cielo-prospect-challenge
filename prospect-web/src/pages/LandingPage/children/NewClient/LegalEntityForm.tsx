/* eslint-disable no-template-curly-in-string */

import InputCNPJ from '../../../../components/InputCNPJ';
import InputCPF from '../../../../components/InputCPF';
import InputMCC from '../../../../components/InputMCC';
import Button from '../../../../facades/Button';
import Divider from '../../../../facades/Divider';
import Form from '../../../../facades/Form';
import Input from '../../../../facades/Input';
import Space from '../../../../facades/Space';

import validateCPF from './validateCpf';

function LegalEntityForm() {
  return (
    <Form
      colon={false}
      layout="vertical"
      onFinish={(values) => {
        console.log(values);
      }}
    >
      <Form.Item
        label="CNPJ"
        name="cnpj"
        rules={[{ required: true, message: 'CNPJ deve ser informado!' }]}
        validateFirst
      >
        <InputCNPJ />
      </Form.Item>
      <Form.Item
        label="razão social"
        name="corporateReason"
        rules={[
          { required: true, message: 'razão social deve ser informada!' },
          {
            required: true,
            message: 'razão não pode conter mais de 50 caracteres!',
            max: 50,
          },
        ]}
        validateFirst
      >
        <Input />
      </Form.Item>
      <Form.Item
        label="MCC"
        name="mcc"
        tooltip="Merchant Category Code"
        rules={[
          { required: true, message: 'MMC deve ser informado!' },
          {
            required: true,
            message: 'MMC só pode conter números!',
            transform: (value) => parseInt(value, 10),
            type: 'integer',
          },
          {
            required: true,
            message: 'MMC só pode conter 4 dígitos!',
            max: 4,
          },
        ]}
        validateFirst
      >
        <InputMCC />
      </Form.Item>
      <Divider>informações do contato do estabelecimento</Divider>
      <Form.Item
        label="CPF"
        name="cpf"
        rules={[
          { required: true, message: 'CPF do contato deve ser informado!' },
          {
            required: true,
            message: 'CPF só pode conter números!',
            transform: (value) => parseInt(value, 10),
            type: 'integer',
          },
          {
            validator: (_, value) =>
              validateCPF(value)
                ? Promise.resolve()
                : Promise.reject(new Error('CPF inválido!')),
          },
        ]}
        validateFirst
      >
        <InputCPF />
      </Form.Item>
      <Form.Item
        label="nome"
        name="name"
        rules={[
          { required: true, message: 'nome do contato deve ser informado!' },
          {
            required: true,
            message: 'nome do contato não pode conter mais de 50 caracteres!',
            max: 50,
          },
        ]}
        validateFirst
      >
        <Input />
      </Form.Item>
      <Form.Item
        label="e-mail"
        name="email"
        rules={[
          { required: true, message: 'e-mail do contato deve ser informado!' },
          { required: true, type: 'email', message: 'e-mail inválido!' },
        ]}
        validateFirst
      >
        <Input />
      </Form.Item>
      <Space>
        <Button htmlType="submit" type="primary">
          Cadastrar
        </Button>
        <Button danger>Cancelar</Button>
      </Space>
    </Form>
  );
}

export default LegalEntityForm;
