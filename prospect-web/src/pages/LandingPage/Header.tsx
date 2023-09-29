import { FaRegAddressBook } from 'react-icons/fa';
import styled from 'styled-components';

import colors from '../../colors';
import Title from '../../components/Title';
import Layout from '../../facades/Layout';

const StyledHeader = styled(Layout.Header)`
  background-color: ${colors.primaryColor};
  display: flex;

  .header-title {
    margin: 0;

    @media screen and (max-width: 544px) {
      font-size: 24px;
    }
    transition: font-size 0.3s ease;
  }
`;

function Header() {
  return (
    <StyledHeader>
      <Title
        label="Portal de pré cadastros"
        iconComponent={FaRegAddressBook}
        className="header-title"
        style={{ color: colors.primaryTitleColor }}
      />
    </StyledHeader>
  );
}

export default Header;
